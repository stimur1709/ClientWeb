package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.UserContactDto;
import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.data.model.user.User;
import com.example.clientweb.data.model.user.UserContact;
import com.example.clientweb.repository.UserContactRepository;
import com.example.clientweb.repository.UserRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.Generator;
import com.example.clientweb.util.MessageLocale;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ModelServiceImpl<User, UserDto, UserRepository> {

    private final PasswordEncoder passwordEncoder;
    private final UserContactRepository userContactRepository;
    private final Generator generator;

    @Autowired
    public UserService(UserRepository repository, ModelMapper modelMapper, MessageLocale messageLocale, PasswordEncoder passwordEncoder, UserContactRepository userContactRepository, Generator generator) {
        super(repository, UserDto.class, User.class, modelMapper, messageLocale);
        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                skip(destination.getPassword());
            }
        });
        this.passwordEncoder = passwordEncoder;
        this.userContactRepository = userContactRepository;
        this.generator = generator;
    }

    @Override
    public UserDto save(UserDto dto) throws Exception {
        User user = findById(dto.getId());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getUserContact().size() > 0) {
            changeMail(dto, user);
        }
        return super.save(dto);
    }

    private void changeMail(UserDto dto, User user) throws Exception {
        UserContactDto contact = dto.getUserContact().get(0);
        Optional<UserContact> contactOpt = userContactRepository.findByContactIgnoreCase(contact.getContact());
        if (contactOpt.isEmpty()) {
            userContactRepository.save(new UserContact(user, contact.getType(), contact.getContact(), generator.getSecretCode()));
        } else if (!contactOpt.get().getUser().getId().equals(user.getId())) {
            throw new Exception(messageLocale.getMessage("message.mailBusy"));
        }
    }

}
