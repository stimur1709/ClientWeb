package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.service.educationService.CourseService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController extends ModelControllerImpl<EducationDto, CourseService> {

    @Autowired
    protected CourseController(CourseService service, BindingResultResponse bindingResultResponse) {
        super(service, bindingResultResponse);
    }

}