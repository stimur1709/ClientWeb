package com.example.clientweb.controller;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.ModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ModelEntityController<E extends ModelEntity, D extends ModelDto> {

    @PostMapping("/save")
    ResponseEntity<E> save(@RequestBody @Valid E dto, BindingResult bindingResult);

    @GetMapping
    Page<E> getPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit,
                    @RequestParam(value = "reverse", required = false) boolean reverse,
                    @RequestParam(value = "sort", required = false) String sort);
}
