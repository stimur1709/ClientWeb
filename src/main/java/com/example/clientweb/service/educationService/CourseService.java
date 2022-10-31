package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Course;
import com.example.clientweb.repository.educationRepository.CourseRepository;
import com.example.clientweb.service.ModelEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends ModelEntityServiceImpl<Course, EducationDto, CourseRepository> {

    @Autowired
    protected CourseService(CourseRepository repository) {
        super(repository);
    }
}
