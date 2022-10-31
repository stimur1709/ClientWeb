package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Education;
import com.example.clientweb.repository.educationRepository.EducationRepository;
import com.example.clientweb.service.ModelEntityServiceImpl;

import java.util.Date;

public abstract class EducationService<E extends Education, D extends EducationDto, R extends EducationRepository<E>>
        extends ModelEntityServiceImpl<E, D, R> {

    public EducationService(R repository) {
        super(repository);
    }

    @Override
    public void save(E entity) {
        entity.setCreatedDate(new Date());
        entity.setUpdatedDate(new Date());
        super.save(entity);
    }
}
