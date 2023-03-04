package com.example.clientweb.service;

import com.example.clientweb.data.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ModelService<E extends Model> {

    Page<E> findAll(int itemType, PageRequest of);

    E findById(Integer id);

    E save(E entity);


}
