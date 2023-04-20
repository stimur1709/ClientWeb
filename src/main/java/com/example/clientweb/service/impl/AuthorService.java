package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.model.Author;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.AuthorRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ModelServiceImpl<Author, AuthorDto, AuthorRepository> {

    private final UserRatingsService userRatingsService;

    @Autowired
    public AuthorService(AuthorRepository repository, ModelMapper modelMapper, MessageLocale messageLocale,
                         UserRatingsService userRatingsService) {
        super(repository, AuthorDto.class, Author.class, modelMapper, messageLocale);
        this.userRatingsService = userRatingsService;
    }

    @Override
    public AuthorDto save(AuthorDto dto) throws SaveException {
        Integer id = dto.getId();
        if (id != null) {
            Author author = findById(id);
            author.setRating(userRatingsService.saveRating(dto));
            author.setName(dto.getName());
            author.setDescription(dto.getDescription());
            repository.save(author);
            return findByIdDto(id);
        } else {
            return super.save(dto);
        }
    }

    @Override
    public Page<AuthorDto> findAll(int itemType, PageRequest pageRequest) {
        return repository.findAllAuthor(2, pageRequest).map(converterToDto());
    }

    @Override
    public AuthorDto findByIdDto(Integer id) {
        return repository.findByAuthor(id, 2).map(converterToDto()).orElse(null);
    }
}
