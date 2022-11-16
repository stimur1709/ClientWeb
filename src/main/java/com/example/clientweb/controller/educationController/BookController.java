package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.service.educationService.BookService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController extends ModelControllerImpl<EducationDto, BookService> {

    @Autowired
    protected BookController(BookService service, BindingResultResponse bindingResultResponse) {
        super(service, bindingResultResponse);
    }

}
