package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Course;
import com.example.clientweb.repository.educationRepository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CourseService extends EducationServiceImpl<Course, EducationDto, CourseRepository>  {

    @Autowired
    public CourseService(CourseRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public void save(EducationDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        course.setUpdatedDate(new Date());
        course.setCreatedDate(new Date());
        repository.save(course);
    }
}
