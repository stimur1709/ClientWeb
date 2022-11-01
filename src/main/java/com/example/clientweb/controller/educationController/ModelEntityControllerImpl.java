package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Education;
import com.example.clientweb.service.educationService.EducationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class ModelEntityControllerImpl<E extends Education, D extends EducationDto, S extends EducationService<E, D>>
        implements ModelEntityController<E, D> {

    protected final S service;

    protected ModelEntityControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public Page<E> getPage(int offset, int limit, boolean reverse, String sort) {
        if (sort == null)
            return service.findAll(offset, limit);
        return service.findAll(offset, limit, reverse, sort);
    }

    @Override
    public ResponseEntity<E> save(D dto, BindingResult bindingResult) {
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
