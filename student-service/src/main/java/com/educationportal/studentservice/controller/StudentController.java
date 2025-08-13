package com.educationportal.studentservice.controller;

import com.educationportal.studentservice.entity.Student;
import com.educationportal.studentservice.repository.StudentRepository;
import com.educationportal.studentservice.service.CourseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentController {

    //Inter service communication
    private final CourseClient courseClient;
    private final StudentRepository studentRepository;

    @GetMapping("/mock-student")
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok("Student Service");
    }
    @GetMapping("/mock-student-course")
    public ResponseEntity<String> getStudentMockCourse() {
        return ResponseEntity.ok(courseClient.getMockCourse());
    }

    // CREATE
    @PostMapping("/save")
    public Student createInstructor(@RequestBody Student instructor) {
        return studentRepository.save(instructor);
    }

    // READ ALL
    @GetMapping("/all")
    public List<Student> getAllInstructors() {
        return studentRepository.findAll();
    }

    // READ by ID
    @GetMapping("/{id}")
    public Optional<Student> getInstructorById(@PathVariable String id) {
        return studentRepository.findById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateInstructor(@PathVariable String id, @RequestBody Student updatedInstructor) {
        return studentRepository.findById(id)
                .map(existingInstructor -> {
                    existingInstructor.setName(updatedInstructor.getName());
                    existingInstructor.setEmail(updatedInstructor.getEmail());
                    return studentRepository.save(existingInstructor);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteInstructor(@PathVariable String id) {
        studentRepository.deleteById(id);
        return "Student with id " + id + " deleted successfully.";
    }
}
