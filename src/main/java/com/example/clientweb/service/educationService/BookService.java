package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Book;
import com.example.clientweb.repository.educationRepository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookService extends EducationServiceImpl<Book, EducationDto, BookRepository> {

    @Autowired
    public BookService(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public void save(EducationDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        book.setUpdatedDate(new Date());
        book.setCreatedDate(new Date());
        repository.save(book);
    }
}
