package com.educationportal.instructorservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    @GetMapping("/mock-instructor")
    public ResponseEntity<String> getInstructor() {
        return ResponseEntity.ok().body("Instructor Service");
    }
}
