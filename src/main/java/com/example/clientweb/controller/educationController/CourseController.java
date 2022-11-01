package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Course;
import com.example.clientweb.service.educationService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController extends ModelEntityControllerImpl<Course, EducationDto, CourseService> {

    @Autowired
    protected CourseController(CourseService service) {
        super(service);
    }

}