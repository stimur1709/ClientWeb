package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.data.model.user.User;
import com.example.clientweb.data.model.user.UserContact;
import com.example.clientweb.repository.UserContactRepository;
import com.example.clientweb.repository.UserRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.Generator;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ModelServiceImpl<User, UserDto, UserRepository> {

    private final PasswordEncoder passwordEncoder;
    private final UserContactRepository userContactRepository;
    private final Generator generator;

    @Autowired
    public UserService(UserRepository repository, ModelMapper modelMapper, MessageLocale messageLocale, PasswordEncoder passwordEncoder, UserContactRepository userContactRepository, Generator generator) {
        super(repository, UserDto.class, modelMapper, messageLocale);
        this.passwordEncoder = passwordEncoder;
        this.userContactRepository = userContactRepository;
        this.generator = generator;
    }

    @Override
    public UserDto save(User model) throws Exception {
        User user = findById(model.getId());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setFirstname(model.getFirstname());
        user.setLastname(model.getLastname());
        if (model.getUserContact().size() > 0) {
            changeMail(model, user);
        }
        return super.save(user);
    }

    private void changeMail(User model, User user) throws Exception {
        UserContact contact = model.getUserContact().get(0);
        Integer userId = userContactRepository.findByContactIgnoreCase(contact.getContact()).getUser().getId();
        if (userId == null) {
            userContactRepository.save(new UserContact(user, contact.getType(), contact.getContact(), generator.getSecretCode()));
        } else if (!userId.equals(user.getId())) {
            throw new Exception(messageLocale.getMessage("message.mailBusy"));
        }
    }

}
