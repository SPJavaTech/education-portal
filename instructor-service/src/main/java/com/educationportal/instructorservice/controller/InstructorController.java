package com.educationportal.instructorservice.controller;

import com.educationportal.instructorservice.entity.Instructor;
import com.educationportal.instructorservice.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorRepository instructorRepository;

    @GetMapping("/mock-instructor")
    public ResponseEntity<String> getInstructor() {
        return ResponseEntity.ok().body("Instructor Service");
    }

    // CREATE
    @PostMapping("/save")
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // READ ALL
    @GetMapping("/all")
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // READ by ID
    @GetMapping("/{id}")
    public Optional<Instructor> getInstructorById(@PathVariable String id) {
        return instructorRepository.findById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Instructor updateInstructor(@PathVariable String id, @RequestBody Instructor updatedInstructor) {
        return instructorRepository.findById(id)
                .map(existingInstructor -> {
                    existingInstructor.setName(updatedInstructor.getName());
                    existingInstructor.setEmail(updatedInstructor.getEmail());
                    return instructorRepository.save(existingInstructor);
                })
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteInstructor(@PathVariable String id) {
        instructorRepository.deleteById(id);
        return "Instructor with id " + id + " deleted successfully.";
    }
}
