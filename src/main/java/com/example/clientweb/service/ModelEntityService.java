package com.example.clientweb.service;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.ModelEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ModelEntityService<E extends ModelEntity, D extends ModelDto> {

    Page<E> findAll(int offset, int limit);

    Page<E> findAll(int offset, int limit, boolean reverse, String sort);

    Optional<E> findById(Integer id);

    void save(E entity);

    E save(D dto);

}
