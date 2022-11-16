package com.example.clientweb.repository.educationRepository;

import com.example.clientweb.model.education.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository<E extends Education> extends JpaRepository<E, Integer> {
}
