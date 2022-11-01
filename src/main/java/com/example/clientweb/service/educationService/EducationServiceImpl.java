package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Education;
import com.example.clientweb.repository.educationRepository.EducationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Optional;

public abstract class EducationServiceImpl<E extends Education, D extends EducationDto, R extends EducationRepository<E>>
        implements EducationService<E, D> {

    protected R repository;
    protected final ModelMapper modelMapper;

    public EducationServiceImpl(R repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<E> findAll(int offset, int limit) {
        return null;
    }

    @Override
    public Page<E> findAll(int offset, int limit, boolean reverse, String sort) {
        return null;
    }

    @Override
    public Optional<E> findById(Integer id) {
        return Optional.empty();
    }
}
