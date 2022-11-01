package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.service.educationService.EducationService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public abstract class ModelControllerImpl<D extends EducationDto, S extends EducationService<D>>
        implements ModelController<D> {

    protected final S service;
    protected final BindingResultResponse bindingResultResponse;

    protected ModelControllerImpl(S service, BindingResultResponse bindingResultResponse) {
        this.service = service;
        this.bindingResultResponse = bindingResultResponse;
    }

    @Override
    public Page<D> getPage(int page, int size, boolean reverse, String sort) {
        if (sort == null)
            return service.findAll(page, size);
        return service.findAll(page, size, reverse, sort);
    }

    @Override
    public ResponseEntity<Map<String, Object>> save(D dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.OK);
        return new ResponseEntity<>(Map.of("result", service.save(dto)), HttpStatus.OK);
    }

    @Override
    public D getEducation(int id) {
        return service.findById(id);
    }
}
