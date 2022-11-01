package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Book;
import com.example.clientweb.repository.educationRepository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BookService extends EducationServiceImpl<Book, EducationDto, BookRepository> {

    @Autowired
    public BookService(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public boolean save(EducationDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        book.setUpdatedDate(new Date());
        book.setCreatedDate(new Date());
        repository.save(book);
        return true;
    }

    @Override
    public Page<EducationDto> findAll(int page, int size) {
        Page<Book> bookPage = repository.findAll(PageRequest.of(page, size));
        return bookPage.map(book -> modelMapper.map(book, EducationDto.class));
    }

    @Override
    public Page<EducationDto> findAll(int page, int size, boolean reverse, String sort) {
        Page<Book> bookPage = repository.findAll(PageRequest.of(page, size, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort));
        return bookPage.map(book -> modelMapper.map(book, EducationDto.class));
    }

    @Override
    public EducationDto findById(Integer id) {
        Optional<Book> bookOptional = repository.findById(id);
        return bookOptional.map(book -> modelMapper.map(book, EducationDto.class)).orElse(null);
    }
}
