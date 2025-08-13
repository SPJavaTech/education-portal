package com.educationportal.instructorservice.repository;


import com.educationportal.instructorservice.entity.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstructorRepository extends MongoRepository<Instructor, String> {

}
