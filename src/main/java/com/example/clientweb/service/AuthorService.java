package com.example.clientweb.service;

import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ModelServiceImpl<Author, AuthorDto, AuthorRepository> {


    @Autowired
    public AuthorService(AuthorRepository repository, ModelMapper modelMapper) {
        super(repository, AuthorDto.class, modelMapper);
    }

    @Override
    public AuthorDto save(Author model) {
        if (model.getId() != null) {
            Author author = findById(model.getId());
            if (author != null) {
                author.setName(model.getName());
                author.setDescription(model.getDescription());
                author.setPhoto(model.getPhoto());
                author.setItems(model.getItems());
            }
            return super.save(author);
        } else {
            return super.save(model);
        }
    }
}
