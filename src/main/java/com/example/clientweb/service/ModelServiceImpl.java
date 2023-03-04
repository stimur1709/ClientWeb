package com.example.clientweb.service;

import com.example.clientweb.data.model.Model;
import com.example.clientweb.repository.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class ModelServiceImpl<E extends Model, R extends EntityRepository<E>>
        implements ModelService<E> {

    protected R repository;

    public ModelServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public Page<E> findAll(int itemType, PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public E findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }
}
