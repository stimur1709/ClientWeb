package com.example.clientweb.controller.impl;

import com.example.clientweb.controller.ModelControllerImpl;
import com.example.clientweb.data.dto.UserRatingsDto;
import com.example.clientweb.data.model.UserRatings;
import com.example.clientweb.service.impl.UserRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ratings")
public class UserRatingsController extends ModelControllerImpl<UserRatingsDto, UserRatings, UserRatingsService> {

    @Autowired
    protected UserRatingsController(UserRatingsService service) {
        super(service);
    }
}
