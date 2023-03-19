package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.errors.SaveException;
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
        super(repository, AuthorDto.class, Author.class, modelMapper, messageLocale);
    }

    @Override
    public AuthorDto save(AuthorDto dto) throws SaveException {
        if (dto.getId() != null) {
            Author author = findById(dto.getId());
                author.setName(dto.getName());
                author.setDescription(dto.getDescription());
            return modelMapper.map(repository.save(author), AuthorDto.class);
        } else {
            return super.save(dto);
        }
    }
}
