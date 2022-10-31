package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Book;
import com.example.clientweb.repository.educationRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends EducationService<Book, EducationDto, BookRepository> {

    @Autowired
    public BookService(BookRepository repository) {
        super(repository);
    }
}
