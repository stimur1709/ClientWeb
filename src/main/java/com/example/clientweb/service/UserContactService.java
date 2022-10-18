package com.example.clientweb.service;

import com.example.clientweb.model.ContactType;
import com.example.clientweb.model.UserContact;
import com.example.clientweb.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<UserContact> findByMail(String mail) {
        return userContactRepository.findByContactAndType(mail, ContactType.MAIL);
    }

    public UserContact getByUsernameAndType(String username, ContactType type) {
        return userContactRepository.findByUser_UsernameAndType(username, type);
    }
}
