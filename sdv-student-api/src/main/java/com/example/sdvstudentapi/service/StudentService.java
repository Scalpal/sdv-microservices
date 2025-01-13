package com.example.sdvstudentapi.service;

import com.example.sdvstudentapi.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getStudent(String id);

    List<StudentDto> getAllStudents();

    String addStudent(StudentDto studentDto);

    String updateStudent(String id, StudentDto studentDto);

    String deleteStudent(String id);

}
