package com.example.clientweb.controller;

import com.example.clientweb.data.model.Model;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

public interface ModelController<E extends Model> {

    @PostMapping(value = "/save")
    ResponseEntity<Map<String, Object>> save(@RequestParam(name = "file", required = false) MultipartFile file,
                                             @ModelAttribute @Valid E entity, BindingResult bindingResult);

    @GetMapping
    @Operation(description = "'page' abd 'size' required parameters for the request. " +
            "key property to sort by 'sort' " +
            "reverse == true ? Sort.Direction.ASC : Sort.Direction.DESC")
    Page<E> getPage(@RequestParam(value = "itemType", defaultValue = "0") int itemType,
                    @RequestParam("page") int page, @RequestParam("size") int size,
                    @RequestParam(value = "reverse", defaultValue = "true") boolean reverse,
                    @RequestParam(value = "sort", defaultValue = "id") String sort);

    @GetMapping("/{id}")
    E getEducation(@PathVariable("id") int id);
}
