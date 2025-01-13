package com.example.sdvschoolapi.mapper;

import com.example.sdvschoolapi.dto.SchoolDto;
import com.example.sdvschoolapi.entity.School;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    SchoolDto toDto(School school);

    School toEntity(SchoolDto schoolDto);

    List<SchoolDto> toDtos(List<School> schools);
}
