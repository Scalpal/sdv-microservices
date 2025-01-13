package com.example.sdvschoolapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    private String name;

    private String adress;

    private String directorName;
}
