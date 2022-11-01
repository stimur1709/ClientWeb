package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import com.example.clientweb.model.education.Education;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ModelEntityController<E extends Education, D extends EducationDto> {

    @PostMapping("/save")
    ResponseEntity<E> save(@RequestBody @Valid D dto, BindingResult bindingResult);

    @GetMapping
    Page<E> getPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit,
                    @RequestParam(value = "reverse", required = false) boolean reverse,
                    @RequestParam(value = "sort", required = false) String sort);
}
