package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Course;
import com.example.clientweb.repository.educationRepository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CourseService extends EducationServiceImpl<Course, EducationDto, CourseRepository> {

    @Autowired
    public CourseService(CourseRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public boolean save(EducationDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        course.setUpdatedDate(new Date());
        course.setCreatedDate(new Date());
        repository.save(course);
        return true;
    }

    @Override
    public Page<EducationDto> findAll(int page, int size) {
        Page<Course> coursePage = repository.findAll(PageRequest.of(page, size));
        return coursePage.map(course -> modelMapper.map(course, EducationDto.class));
    }

    @Override
    public Page<EducationDto> findAll(int page, int size, boolean reverse, String sort) {
        Page<Course> coursePage = repository.findAll(PageRequest.of(page, size, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort));
        return coursePage.map(course -> modelMapper.map(course, EducationDto.class));
    }

    @Override
    public EducationDto findById(Integer id) {
        Optional<Course> courseOptional = repository.findById(id);
        return courseOptional.map(course -> modelMapper.map(course, EducationDto.class)).orElse(null);
    }
}
