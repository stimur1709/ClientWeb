package com.example.clientweb.controller.educationController;

import com.example.clientweb.dto.EducationDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

public interface ModelController<D extends EducationDto> {

    @PostMapping("/save")
    ResponseEntity<Map<String, Object>> save(@RequestBody @Valid D dto, BindingResult bindingResult);

    @GetMapping
    @Operation(description = "'page' abd 'size' required parameters for the request. " +
            "key property to sort by 'sort' " +
            "reverse == true ? Sort.Direction.ASC : Sort.Direction.DESC")
    Page<D> getPage(@RequestParam("page") int page, @RequestParam("size") int size,
                    @RequestParam(value = "reverse", required = false) boolean reverse,
                    @RequestParam(value = "sort", required = false) String sort);

    @GetMapping("/{id}")
    D getEducation(@PathVariable("id") int id);
}
