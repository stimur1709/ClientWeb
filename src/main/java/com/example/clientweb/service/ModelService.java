package com.example.clientweb.service;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import com.example.clientweb.errors.SaveException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ModelService<D extends Dto, M extends Model> {

    Page<D> findAll(int itemType, PageRequest of);

    D findByIdDto(Integer id);

    M findById(Integer id);

    D save(D dto) throws SaveException;
    D save(M model) throws SaveException;

    List<D> saveAll(List<M> list);

}
