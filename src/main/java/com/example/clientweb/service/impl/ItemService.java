package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.Item;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ItemRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemService extends ModelServiceImpl<Item, ItemDto, ItemRepository> {

    @Autowired
    public ItemService(ItemRepository repository, ModelMapper modelMapper, MessageLocale messageLocale) {
        super(repository, ItemDto.class, Item.class, modelMapper, messageLocale);
    }

    @Override
    public Page<ItemDto> findAll(int itemType, PageRequest pageRequest) {
        if (Arrays.asList(1, 2).contains(itemType)) {
            return repository.findByTypeId(itemType, pageRequest).map(m -> modelMapper.map(m, ItemDto.class));
        } else {
            return super.findAll(itemType, pageRequest);
        }
    }

    @Override
    public ItemDto save(ItemDto dto) throws SaveException {
        if (dto.getId() != null) {
            Item itemDto = modelMapper.map(dto, Item.class);
            Item item = findById(dto.getId());
            item.setTitle(dto.getTitle());
            item.setDescription(dto.getDescription());
            item.setImage(itemDto.getImage());
            item.setAuthors(itemDto.getAuthors());
            return modelMapper.map(repository.save(item), ItemDto.class);
        } else {
            return super.save(dto);
        }
    }

}
