package com.example.clientweb.service;

import com.example.clientweb.data.model.Item;
import com.example.clientweb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemService extends ModelServiceImpl<Item, ItemRepository> {

    @Autowired
    public ItemService(ItemRepository repository) {
        super(repository);
    }

    @Override
    public Page<Item> findAll(int itemType, PageRequest pageRequest) {
        if (Arrays.asList(1, 2).contains(itemType)) {
            return repository.findByTypeId(itemType, pageRequest);
        } else {
            return super.findAll(itemType, pageRequest);
        }
    }
}
