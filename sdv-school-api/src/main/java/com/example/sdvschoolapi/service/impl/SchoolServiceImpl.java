package com.example.sdvschoolapi.service.impl;

import com.example.sdvschoolapi.dto.SchoolDto;
import com.example.sdvschoolapi.entity.School;
import com.example.sdvschoolapi.mapper.SchoolMapper;
import com.example.sdvschoolapi.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public SchoolDto getSchool(Long id) {
        School schoolEntity = schoolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("School not found"));

        return schoolMapper.toDto(schoolEntity);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        List<School> schoolEntities = schoolRepository.findAll();

        return schoolMapper.toDtos(schoolEntities);
    }

    @Override
    public String addSchool(SchoolDto schoolDto) {
        School newSchool = schoolMapper.toEntity(schoolDto);

        schoolRepository.save(newSchool);

        return "School successfully created";
    }

    @Override
    public String updateSchool(Long id, SchoolDto schoolDto) {
        School currentSchool = schoolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("School not found"));

        School updatedSchool = schoolMapper.toEntity(schoolDto);
        updatedSchool.setId(id);

        schoolRepository.save(updatedSchool);

        return "School successfully updated";
    }

    @Override
    public String deleteSchool(Long id) {
        School currentSchool = schoolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("School not found"));

        schoolRepository.deleteById(id);

        return "School successfully deleted";
    }

}
