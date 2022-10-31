package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.education.Education;
import com.example.clientweb.repository.educationRepository.EducationRepository;
import com.example.clientweb.service.ModelEntityServiceImpl;

public abstract class EducationService<E extends Education, D extends ModelDto,
        R extends EducationRepository<E>> extends ModelEntityServiceImpl<E, D, R> {

    public EducationService(R repository) {
        super(repository);
    }

}
