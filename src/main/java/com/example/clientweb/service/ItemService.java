package com.example.clientweb.service;

import com.example.clientweb.data.model.Item;
import com.example.clientweb.repository.ItemRepository;
import com.example.clientweb.util.ResourceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ItemService extends ModelServiceImpl<Item, ItemRepository> {

    private final ResourceStorage resourceStorage;

    @Autowired
    public ItemService(ItemRepository repository, ResourceStorage resourceStorage) {
        super(repository);
        this.resourceStorage = resourceStorage;
    }

    @Override
    public Page<Item> findAll(int itemType, PageRequest pageRequest) {
        if (Arrays.asList(1, 2).contains(itemType)) {
            return repository.findByTypeId(itemType, pageRequest);
        } else {
            return super.findAll(itemType, pageRequest);
        }
    }

    @Override
    public Item save(Item entity, MultipartFile file) {
        if (file != null) {
            try {
                entity.setImage(resourceStorage.saveImage(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return super.save(entity, file);
    }
}
