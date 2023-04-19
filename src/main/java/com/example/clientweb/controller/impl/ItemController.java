package com.example.clientweb.controller.impl;

import com.example.clientweb.controller.ModelControllerImpl;
import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.Item;
import com.example.clientweb.service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController extends ModelControllerImpl<ItemDto, Item, ItemService> {

    @Autowired
    protected ItemController(ItemService service) {
        super(service);
    }

}
