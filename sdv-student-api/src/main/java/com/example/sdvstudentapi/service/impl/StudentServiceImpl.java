package com.example.sdvstudentapi.service.impl;

import com.example.sdvstudentapi.dto.StudentDto;
import com.example.sdvstudentapi.entity.Student;
import com.example.sdvstudentapi.mapper.StudentMapper;
import com.example.sdvstudentapi.repository.StudentRepository;
import com.example.sdvstudentapi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto getStudent(String id) {
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return studentMapper.toDto(studentEntity);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentEntities = studentRepository.findAll();

        return studentMapper.toDtos(studentEntities);
    }

    @Override
    public String addStudent(StudentDto studentDto) {
        Student newStudent = studentMapper.toEntity(studentDto);

        studentRepository.save(newStudent);

        return "Student successfully created";
    }

    @Override
    public String updateStudent(String id, StudentDto studentDto) {
        Student currentStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Student updatedStudent = studentMapper.toEntity(studentDto);

        studentRepository.save(updatedStudent);

        return "Student successfully updated";
    }

    @Override
    public String deleteStudent(String id) {
        Student currentStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        studentRepository.deleteById(id);

        return "Student successfully deleted";
    }

}
