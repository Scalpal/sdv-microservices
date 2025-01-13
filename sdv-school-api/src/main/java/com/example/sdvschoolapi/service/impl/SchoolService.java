package com.example.sdvschoolapi.service.impl;

import com.example.sdvschoolapi.dto.SchoolDto;

import java.util.List;

public interface SchoolService {
    SchoolDto getSchool(Long id);

    List<SchoolDto> getAllSchools();

    String addSchool(SchoolDto schoolDto);

    String updateSchool(Long id, SchoolDto schoolDto);

    String deleteSchool(Long id);

}
