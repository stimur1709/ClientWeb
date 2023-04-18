package com.example.clientweb.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ModelMapperUtil<M, D> {

    private final ModelMapper modelMapper;
    private final Class<D> dto;
    private final Class<M> model;

    @Autowired
    public ModelMapperUtil(ModelMapper modelMapper, Class<D> dto, Class<M> model) {
        this.modelMapper = modelMapper;
        this.dto = dto;
        this.model = model;
    }

    public Function<M, D> converterToDto() {
        return e -> modelMapper.map(e, this.dto);
    }

    public D converterToDto(M m) {
        return modelMapper.map(m, this.dto);
    }

    public M converterToModel(D d) {
        return modelMapper.map(d, this.model);
    }

}
