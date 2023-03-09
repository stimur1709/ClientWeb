package com.example.clientweb.controller;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import com.example.clientweb.service.ModelService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class ModelControllerImpl<D extends Dto, M extends Model, S extends ModelService<D, M>>
        implements ModelController<D, M> {

    protected final S service;
    protected final BindingResultResponse bindingResultResponse;

    protected ModelControllerImpl(S service, BindingResultResponse bindingResultResponse) {
        this.service = service;
        this.bindingResultResponse = bindingResultResponse;
    }

    @Override
    public ResponseEntity<Page<D>> getPage(int itemType, int page, int size, boolean reverse, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        Page<D> all = service.findAll(itemType, pageRequest);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(M model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.save(model), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<D> getEducation(int id) {
        return new ResponseEntity<>(service.findByIdDto(id), HttpStatus.OK);
    }
}
