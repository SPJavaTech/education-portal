package com.educationportal.authservice.config;

import com.educationportal.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/github")
                        .successHandler((request, response, authentication) -> {
                            OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
                            String username = oauthUser.getAttribute("login");
                            String email = oauthUser.getAttribute("email");
                            String avatarUrl = oauthUser.getAttribute("avatar_url");

                            String token = jwtService.generateToken(username);

                            response.setContentType("application/json");
                            response.getWriter().write(
                                    String.format(
                                            "{\"token\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"avatar_url\":\"%s\"}",
                                            token, username, email, avatarUrl
                                    )
                            );
                        })
                );

        return http.build();
    }
}
