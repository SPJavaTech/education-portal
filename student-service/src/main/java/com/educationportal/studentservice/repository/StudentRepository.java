package com.educationportal.studentservice.repository;

import com.educationportal.studentservice.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
