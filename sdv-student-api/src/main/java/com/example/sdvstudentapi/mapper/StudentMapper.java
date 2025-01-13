package com.example.sdvstudentapi.mapper;

import com.example.sdvstudentapi.dto.StudentDto;
import com.example.sdvstudentapi.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);

    Student toEntity(StudentDto studentDto);

    List<StudentDto> toDtos(List<Student> students);
}
