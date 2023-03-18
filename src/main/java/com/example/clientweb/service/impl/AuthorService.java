package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.repository.AuthorRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ModelServiceImpl<Author, AuthorDto, AuthorRepository> {

    @Autowired
    public AuthorService(AuthorRepository repository, ModelMapper modelMapper, MessageLocale messageLocale) {
        super(repository, AuthorDto.class, modelMapper, messageLocale);
    }

    @Override
    public AuthorDto save(Author model) throws Exception {
        if (model.getId() != null) {
            Author author = findById(model.getId());
            if (author != null) {
                author.setName(model.getName());
                author.setDescription(model.getDescription());
                author.setItems(model.getItems());
            }
            return super.save(author);
        } else {
            return super.save(model);
        }
    }
}
