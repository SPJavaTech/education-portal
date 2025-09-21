package com.educationportal.authservice.controller;

import com.educationportal.authservice.dto.AuthResponse;
import com.educationportal.authservice.dto.UserDto;
import com.educationportal.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @Value("${jwt.expiration}")
    private Long expiration;



    /**
     * OAuth2 callback to issue JWT token
     */
  /*  @GetMapping("/callback")
    public AuthResponse callback(Authentication authentication) {
        OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
        String username = oauthUser.getAttribute("login");
        String jwt = jwtService.generateToken(username);
        return new AuthResponse(jwt, username);
    }*/

    /**
     * Get authenticated user details
     */
    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = oauthToken.getPrincipal();

        return new UserDto(
                oauthUser.getAttribute("id").toString(),
                oauthUser.getAttribute("name"),
                oauthUser.getAttribute("email"),
                oauthUser.getAttribute("login"),
                oauthUser.getAttribute("avatar_url")
        );
    }

    @GetMapping("/token")
    public AuthResponse getToken(Authentication authentication) {
        OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
        String username = oauthUser.getAttribute("login");
        String jwt = jwtService.generateToken(username);
        return new AuthResponse(jwt, username);
    }

}
