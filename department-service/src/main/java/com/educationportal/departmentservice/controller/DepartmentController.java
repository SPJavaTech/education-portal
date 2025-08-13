package com.educationportal.departmentservice.controller;

import com.educationportal.departmentservice.entity.Department;
import com.educationportal.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping("/mock-department")
    public ResponseEntity<String> getDepartment() {
        return ResponseEntity.ok("Department Service");
    }


    @PostMapping("/save")
    public Department create(@RequestBody Department dept) {
        return departmentRepository.save(dept);
    }

    @GetMapping("/all")
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department dept) {
        dept.setId(id);
        return departmentRepository.save(dept);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
