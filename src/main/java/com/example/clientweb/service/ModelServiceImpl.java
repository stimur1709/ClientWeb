package com.example.clientweb.service;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ModelRepository;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ModelServiceImpl<M extends Model, D extends Dto, R extends ModelRepository<M>>
        implements ModelService<D, M> {

    protected R repository;
    protected final ModelMapper modelMapper;
    protected MessageLocale messageLocale;
    private final Class<D> dto;
    private final Class<M> model;

    public ModelServiceImpl(R repository, Class<D> dto, Class<M> model, ModelMapper modelMapper, MessageLocale messageLocale) {
        this.repository = repository;
        this.dto = dto;
        this.modelMapper = modelMapper;
        this.messageLocale = messageLocale;
        this.model = model;
    }

    @Override
    public Page<D> findAll(int itemType, PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(converterToDto());
    }

    @Override
    public D findByIdDto(Integer id) {
        return repository.findById(id).map(converterToDto()).orElse(null);
    }

    @Override
    public D save(D dto) throws SaveException {
        M model = converterToModel(dto);
        return converterToDto(repository.save(model));
    }

    @Override
    public M findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<D> saveAll(List<M> list) {
        return repository.saveAll(list).stream().map(converterToDto()).collect(Collectors.toList());
    }

    protected Function<M, D> converterToDto() {
        return e -> modelMapper.map(e, dto);
    }

    protected M converterToModel(D dto) {
        return modelMapper.map(dto, this.model);
    }

    protected D converterToDto(M model) {
        return modelMapper.map(model, this.dto);
    }
}
