package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.AuthorDto;
import com.example.clientweb.data.dto.Dto;
import com.example.clientweb.data.dto.ItemDto;
import com.example.clientweb.data.model.UserRatings;
import com.example.clientweb.repository.UserRatingsRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.service.UserAuthService;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatingsService extends ModelServiceImpl<UserRatings, Dto, UserRatingsRepository> {

    @Autowired
    public UserRatingsService(UserRatingsRepository repository, ModelMapper modelMapper,
                              MessageLocale messageLocale, UserAuthService userAuthService) {
        super(repository, Dto.class, UserRatings.class, modelMapper, messageLocale, userAuthService);
    }

    public Integer saveRating(ItemDto dto) {
        Integer userId = getUserAuthId();
        if (dto.getRating() != null && userId != null) {
            repository.saveItemRating(userId, dto.getId(), dto.getRating());
        }
        return dto.getRating();
    }

    public Integer saveRating(AuthorDto dto) {
        Integer userId = getUserAuthId();
        if (dto.getRating() != null && userId != null) {
            repository.saveAuthorRating(userId, dto.getId(), dto.getRating());
        }
        return dto.getRating();
    }
}
