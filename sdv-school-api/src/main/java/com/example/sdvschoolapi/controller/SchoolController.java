package com.example.sdvschoolapi.controller;

import com.example.sdvschoolapi.dto.SchoolDto;
import com.example.sdvschoolapi.service.impl.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping()
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        return new ResponseEntity<>(schoolService.getAllSchools(), HttpStatus.OK);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<SchoolDto> getSchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(schoolService.getSchool(schoolId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addSchool(@RequestBody SchoolDto schoolDto) {
        return new ResponseEntity<>(schoolService.addSchool(schoolDto), HttpStatus.OK);
    }

    @PostMapping("/{schoolId}")
    public ResponseEntity<String> updateSchool(@PathVariable Long schoolId, @RequestBody SchoolDto schoolDto) {
        return new ResponseEntity<>(schoolService.updateSchool(schoolId, schoolDto), HttpStatus.OK);
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(schoolService.deleteSchool(schoolId), HttpStatus.OK);
    }
}
