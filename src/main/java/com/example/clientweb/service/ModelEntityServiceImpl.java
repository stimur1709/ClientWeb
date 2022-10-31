package com.example.clientweb.service;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.ModelEntity;
import com.example.clientweb.repository.ModelEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public abstract class ModelEntityServiceImpl<E extends ModelEntity, D extends ModelDto, R extends ModelEntityRepository<E>>
        implements ModelEntityService<E, D> {

    protected final R repository;

    public ModelEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public Page<E> findAll(int offset, int limit) {
        return repository.findAll(PageRequest.of(offset, limit));
    }

    @Override
    public Page<E> findAll(int offset, int limit, boolean reverse, String sort) {
        return repository.findAll(PageRequest.of(offset, limit, reverse ? Sort.Direction.ASC : Sort.Direction.DESC, sort));
    }

    @Override
    public Optional<E> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void save(E entity) {
        repository.save(entity);
    }

}
