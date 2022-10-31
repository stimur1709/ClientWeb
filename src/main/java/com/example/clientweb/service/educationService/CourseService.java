package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Course;
import com.example.clientweb.repository.educationRepository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends EducationService<Course, EducationDto, CourseRepository> {

    @Autowired
    protected CourseService(CourseRepository repository) {
        super(repository);
    }
}
