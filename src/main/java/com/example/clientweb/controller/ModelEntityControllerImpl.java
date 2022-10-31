package com.example.clientweb.controller;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.ModelEntity;
import com.example.clientweb.service.ModelEntityService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class ModelEntityControllerImpl<E extends ModelEntity, D extends ModelDto, S extends ModelEntityService<E, D>>
        implements ModelEntityController<E, D> {

    private final S service;

    protected ModelEntityControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> save(E dto, BindingResult bindingResult) {
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Page<E> getPage(int offset, int limit, boolean reverse, String sort) {
        return service.findAll(offset, limit);
    }
}
