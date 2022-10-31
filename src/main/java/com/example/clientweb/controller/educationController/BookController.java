package com.example.clientweb.controller.educationController;

import com.example.clientweb.controller.ModelEntityControllerImpl;
import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Book;
import com.example.clientweb.service.educationService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController extends ModelEntityControllerImpl<Book, EducationDto, BookService> {

    @Autowired
    protected BookController(BookService service) {
        super(service);
    }
}
