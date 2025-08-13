package com.educationportal.studentservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CourseClient {

    private final WebClient webClient;

    public CourseClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getMockCourse() {
       return webClient.get()
                .uri("http://localhost:6083//api/v1/mock-course")
                .retrieve()
                .bodyToMono(String.class)// expects JSON or text
                .block(); //blocking here just for simplicity in testing
    }
}
