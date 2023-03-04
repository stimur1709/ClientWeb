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

import java.util.Map;

public abstract class ModelControllerImpl<E extends Model, S extends ModelService<E>>
        implements ModelController<E> {

    protected final S service;
    protected final BindingResultResponse bindingResultResponse;

    protected ModelControllerImpl(S service, BindingResultResponse bindingResultResponse) {
        this.service = service;
        this.bindingResultResponse = bindingResultResponse;
    }

    @Override
    public Page<E> getPage(int itemType, int page, int size, boolean reverse, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        return service.findAll(itemType, pageRequest);
    }

    @Override
    public ResponseEntity<Map<String, Object>> save(MultipartFile file, E entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("result", true, entity.getClass().getName(), service.save(entity, file)),
                HttpStatus.OK);
    }

    @Override
    public E getEducation(int id) {
        return service.findById(id);
    }
}
