package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.UserRatingsDto;
import com.example.clientweb.data.model.UserRatings;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.UserRatingsRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.service.UserAuthService;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatingsService extends ModelServiceImpl<UserRatings, UserRatingsDto, UserRatingsRepository> {

    @Autowired
    public UserRatingsService(UserRatingsRepository repository, ModelMapper modelMapper,
                              MessageLocale messageLocale, UserAuthService userAuthService) {
        super(repository, UserRatingsDto.class, UserRatings.class, modelMapper, messageLocale, userAuthService);
    }

    @Override
    public UserRatingsDto save(UserRatingsDto dto) throws SaveException {
        if (dto.getItemId() != null) {
            repository.saveItemRating(getUserAuthId(), dto.getItemId(), dto.getValue());
        } else {
            repository.saveAuthorRating(getUserAuthId(), dto.getAuthorId(), dto.getValue());
        }
        return dto;
    }

}
