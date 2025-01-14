package com.example.sdvstudentapi.controller;

import com.example.sdvstudentapi.dto.SchoolDto;
import com.example.sdvstudentapi.dto.StudentDto;
import com.example.sdvstudentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;
    private final RestTemplate restTemplate;

    public StudentController(StudentService studentService, RestTemplate restTemplate) {
        this.studentService = studentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String studentId) {
        StudentDto student = studentService.getStudent(studentId);

        String eurekaServerSchoolApiUrl = "http://sdv-school-api/api/school/" + student.getSchoolId();
        String url = "http://localhost:8080/api/school/" + student.getSchoolId();

        SchoolDto school = restTemplate.getForObject(eurekaServerSchoolApiUrl, SchoolDto.class);

        if(school != null) {
            student.setSchool(school);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable String studentId, @RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, studentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {
        return new ResponseEntity<>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }
}
