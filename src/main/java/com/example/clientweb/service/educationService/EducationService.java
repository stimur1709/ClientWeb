package com.example.clientweb.service.educationService;

import com.example.clientweb.dto.Dto;
import org.springframework.data.domain.Page;

public interface EducationService<D extends Dto> {

    Page<D> findAll(int offset, int limit);

    Page<D> findAll(int offset, int limit, boolean reverse, String sort);

    D findById(Integer id);

    boolean save(D dto);

}
