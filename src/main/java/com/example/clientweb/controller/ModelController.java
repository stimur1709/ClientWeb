package com.example.clientweb.controller;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ModelController<D extends Dto, M extends Model> {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@RequestBody @Valid M model, BindingResult bindingResult);

    @GetMapping
    @Operation(description = "'page' abd 'size' required parameters for the request. " +
            "key property to sort by 'sort' " +
            "reverse == true ? Sort.Direction.ASC : Sort.Direction.DESC")
    ResponseEntity<Page<D>> getPage(@RequestParam(value = "itemType", defaultValue = "0") int itemType,
                    @RequestParam("page") int page, @RequestParam("size") int size,
                    @RequestParam(value = "reverse", defaultValue = "true") boolean reverse,
                    @RequestParam(value = "sort", defaultValue = "id") String sort);

    @GetMapping("/{id}")
    ResponseEntity<D> getEducation(@PathVariable("id") int id);
}
