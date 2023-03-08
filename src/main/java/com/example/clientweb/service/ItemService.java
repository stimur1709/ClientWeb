package com.example.clientweb.service;

import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.Item;
import com.example.clientweb.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemService extends ModelServiceImpl<Item, ItemDto, ItemRepository> {

    @Autowired
    public ItemService(ItemRepository repository, ModelMapper modelMapper) {
        super(repository, ItemDto.class, modelMapper);
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
    public ItemDto save(Item model) {
        if (model.getId() != null) {
            Item item = findById(model.getId());
            if (item != null) {
                item.setTitle(model.getTitle());
                item.setDescription(model.getDescription());
                item.setImage(model.getImage());
                item.setAuthors(model.getAuthors());
            }
            return super.save(item);
        } else {
            return super.save(model);
        }
    }
}
