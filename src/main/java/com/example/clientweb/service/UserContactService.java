package com.example.clientweb.service;

import com.example.clientweb.model.UserContact;
import com.example.clientweb.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserContactService {

    private final UserContactRepository userContactRepository;

    @Autowired
    public UserContactService(UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    public void save(UserContact userContact) {
        userContactRepository.save(userContact);
    }
}
