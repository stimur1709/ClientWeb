package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.AuthorDtoForAuthor;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.AuthorRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import com.example.clientweb.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ModelServiceImpl<Author, AuthorDtoForAuthor, AuthorRepository> {

    @Autowired
    public AuthorService(AuthorRepository repository, MessageLocale messageLocale,
                         ModelMapperUtil<Author, AuthorDtoForAuthor> modelMapper) {
        super(repository, messageLocale, modelMapper);
    }

    @Override
    public Page<AuthorDtoForAuthor> findAll(int itemType, PageRequest pageRequest) {
        return super.findAll(itemType, pageRequest);
    }

    @Override
    public AuthorDtoForAuthor save(AuthorDtoForAuthor dto) throws SaveException {
        if (dto.getId() != null) {
            Author author = findById(dto.getId());
            author.setName(dto.getName());
            author.setDescription(dto.getDescription());
            return modelMapper.converterToDto(repository.save(author));
        } else {
            return super.save(dto);
        }
    }
}
