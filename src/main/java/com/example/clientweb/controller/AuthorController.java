package com.example.clientweb.controller;

import com.example.clientweb.data.model.Author;
import com.example.clientweb.service.AuthorService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController extends ModelControllerImpl<Author, AuthorService> {

    @Autowired
    protected AuthorController(AuthorService service, BindingResultResponse bindingResultResponse) {
        super(service, bindingResultResponse);
    }
}
