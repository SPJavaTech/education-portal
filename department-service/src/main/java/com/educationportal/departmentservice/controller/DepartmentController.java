package com.educationportal.departmentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @GetMapping("/mock-department")
    public ResponseEntity<String> getDepartment() {
        return ResponseEntity.ok("Department Service");
    }
}
