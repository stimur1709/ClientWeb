package com.example.clientweb.service;

import com.example.clientweb.dto.ModelDto;
import com.example.clientweb.model.ModelEntity;
import com.example.clientweb.repository.ModelEntityRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ModelEntityServiceImpl<E extends ModelEntity, D extends ModelDto, R extends ModelEntityRepository<E>>
        implements ModelEntityService<E, D> {

    protected final R repository;
    private ModelMapper modelMapper;
    private Class<E> modelEntity;
    private Class<D> modelDTO;

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

    @Override
    public E save(D dto) {
        return repository.save(modelMapper.map(dto, modelEntity));
    }

    public static <E, D> Page<D> convertList(Page<D> page, Function<D, E> converter) {
        return (Page<D>) page.map(e -> converter.apply(e));
    }
}
