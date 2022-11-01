package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.Dto;
import com.example.clientweb.model.Entity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EducationService<E extends Entity, D extends Dto> {

    Page<E> findAll(int offset, int limit);

    Page<E> findAll(int offset, int limit, boolean reverse, String sort);

    Optional<E> findById(Integer id);

    void save(D dto);

}
