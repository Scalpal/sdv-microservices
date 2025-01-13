package com.example.sdvschoolapi.repository;

import com.example.sdvschoolapi.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
