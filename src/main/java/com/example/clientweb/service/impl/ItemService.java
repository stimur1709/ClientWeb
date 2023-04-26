package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.Item;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ItemRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.service.UserAuthService;
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
    public ItemService(ItemRepository repository, ModelMapper modelMapper, MessageLocale messageLocale,
                       UserAuthService userAuthService) {
        super(repository, ItemDto.class, Item.class, modelMapper, messageLocale, userAuthService);
    }

    @Override
    public Page<ItemDto> findAll(int itemType, PageRequest pageRequest) {
        if (Arrays.asList(1, 2).contains(itemType)) {
            return repository.findItemsByTypeId(itemType, getUserAuthId(), pageRequest).map(converterToDto());
        } else {
            return repository.findItems(getUserAuthId(), pageRequest).map(converterToDto());
        }
    }

    @Override
    public ItemDto findByIdDto(Integer id) {
        return repository.findByItem(id, getUserAuthId()).map(converterToDto()).orElse(null);
    }

    @Override
    public ItemDto save(ItemDto dto) throws SaveException {
        Integer id = dto.getId();
        if (id != null) {
            Item itemDto = converterToModel(dto);
            Item item = findById(id);
            item.setTitle(dto.getTitle());
            item.setDescription(dto.getDescription());
            item.setImage(itemDto.getImage());
            item.setAuthors(itemDto.getAuthors());
            repository.save(item);
            return converterToDto(repository.save(item));
        } else {
            return super.save(dto);
        }
    }

}
