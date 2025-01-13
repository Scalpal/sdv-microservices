package com.example.sdvstudentapi.repository;

import com.example.sdvstudentapi.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
