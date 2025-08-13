package com.educationportal.courseservice.controller;

import com.educationportal.courseservice.entity.Course;
import com.educationportal.courseservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping("/mock-course")
    public ResponseEntity<String> getCourse() {
        return ResponseEntity.ok("This is the course");
    }

    @PostMapping("/save")
    public Course create(@RequestBody Course dept) {
        return courseRepository.save(dept);
    }

    @GetMapping("/all")
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course dept) {
        dept.setId(id);
        return courseRepository.save(dept);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}
