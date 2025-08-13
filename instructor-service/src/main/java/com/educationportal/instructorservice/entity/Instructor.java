package com.educationportal.instructorservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "instructors")
@Getter
@Setter
public class Instructor {
    @Id
    private String id;

    private String name;
    private String email;
}
