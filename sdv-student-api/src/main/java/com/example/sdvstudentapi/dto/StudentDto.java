package com.example.sdvstudentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String name;

    private String gender;

    private Long schoolId;

    private SchoolDto school = null;
}
