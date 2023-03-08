package com.example.clientweb.service;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ModelService<D extends Dto, M extends Model> {

    Page<D> findAll(int itemType, PageRequest of);

    D findByIdDto(Integer id);

    M findById(Integer id);

    D save(M model);

}
