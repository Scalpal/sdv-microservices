package com.example.sdvstudentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {
    private String name;

    private String adress;

    private String directorName;
}
