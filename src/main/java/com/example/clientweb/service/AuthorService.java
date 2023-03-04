package com.example.clientweb.service;

import com.example.clientweb.data.model.Author;
import com.example.clientweb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ModelServiceImpl<Author, AuthorRepository> {

    @Autowired
    public AuthorService(AuthorRepository repository) {
        super(repository);
    }

}
