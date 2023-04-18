package com.example.clientweb.service;

import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.model.Model;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ModelRepository;
import com.example.clientweb.util.MessageLocale;
import com.example.clientweb.util.ModelMapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelServiceImpl<M extends Model, D extends Dto, R extends ModelRepository<M>>
        implements ModelService<D, M> {

    protected R repository;
    protected MessageLocale messageLocale;
    protected ModelMapperUtil<M, D> modelMapper;

    public ModelServiceImpl(R repository, MessageLocale messageLocale, ModelMapperUtil<M, D> modelMapper) {
        this.repository = repository;
        this.messageLocale = messageLocale;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<D> findAll(int itemType, PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(modelMapper.converterToDto());
    }

    @Override
    public D findByIdDto(Integer id) {
        return repository.findById(id).map(modelMapper.converterToDto()).orElse(null);
    }

    @Override
    public D save(D dto) throws SaveException {
        M model = modelMapper.converterToModel(dto);
        return modelMapper.converterToDto(repository.save(model));
    }

    @Override
    public M findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<D> saveAll(List<M> list) {
        return repository.saveAll(list).stream().map(modelMapper.converterToDto()).collect(Collectors.toList());
    }


}
