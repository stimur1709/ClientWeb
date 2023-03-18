package com.example.clientweb.controller.impl;

import com.example.clientweb.controller.ModelControllerImpl;
import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.service.impl.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController extends ModelControllerImpl<AuthorDto, Author, AuthorService> {

    @Autowired
    protected AuthorController(AuthorService service) {
        super(service);
    }
}
