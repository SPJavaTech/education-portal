package com.educationportal.studentservice.controller;

import com.educationportal.studentservice.service.CourseClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    //Inter service communication
    private final CourseClient courseClient;

    public StudentController(CourseClient courseClient) {
        this.courseClient = courseClient;
    }

    @GetMapping("/mock-student")
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok("Student Service");
    }
    @GetMapping("/mock-student-course")
    public ResponseEntity<String> getStudentMockCourse() {
        return ResponseEntity.ok(courseClient.getMockCourse());
    }

}
