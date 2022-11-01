package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Education;
import com.example.clientweb.repository.educationRepository.EducationRepository;
import org.modelmapper.ModelMapper;

public abstract class EducationServiceImpl<E extends Education, D extends EducationDto, R extends EducationRepository<E>>
        implements EducationService<D> {

    protected R repository;
    protected final ModelMapper modelMapper;

    public EducationServiceImpl(R repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
