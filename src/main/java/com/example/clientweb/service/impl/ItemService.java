package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.Item;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ItemRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import com.example.clientweb.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemService extends ModelServiceImpl<Item, ItemDto, ItemRepository> {

    @Autowired
    public ItemService(ItemRepository repository, MessageLocale messageLocale, ModelMapperUtil<Item, ItemDto> modelMapper) {
        super(repository, messageLocale, modelMapper);
    }

    @Override
    public Page<ItemDto> findAll(int itemType, PageRequest pageRequest) {
        if (Arrays.asList(1, 2).contains(itemType)) {
            return repository.findByTypeId(itemType, pageRequest).map(modelMapper.converterToDto());
        } else {
            return super.findAll(itemType, pageRequest);
        }
    }

    @Override
    public ItemDto save(ItemDto dto) throws SaveException {
        if (dto.getId() != null) {
            Item itemDto = modelMapper.converterToModel(dto);
            Item item = findById(dto.getId());
            item.setTitle(dto.getTitle());
            item.setDescription(dto.getDescription());
            item.setImage(itemDto.getImage());
            item.setAuthors(itemDto.getAuthors());
            return modelMapper.converterToDto(repository.save(item));
        } else {
            return super.save(dto);
        }
    }

}
