package com.example.clientweb.controller;

import com.example.clientweb.data.model.Model;
import com.example.clientweb.service.ModelService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

public abstract class ModelControllerImpl<E extends Model, S extends ModelService<E>>
        implements ModelController<E> {

    protected final S service;
    protected final BindingResultResponse bindingResultResponse;

    protected ModelControllerImpl(S service, BindingResultResponse bindingResultResponse) {
        this.service = service;
        this.bindingResultResponse = bindingResultResponse;
    }

    @Override
    public ResponseEntity<Page<E>> getPage(int itemType, int page, int size, boolean reverse, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        return new ResponseEntity<>(service.findAll(itemType, pageRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(MultipartFile file, E entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.save(entity, file), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<E> getEducation(int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
