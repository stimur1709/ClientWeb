package com.example.clientweb.service.userService;

import com.example.clientweb.data.dto.PasswordDto;
import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.data.model.user.ContactType;
import com.example.clientweb.data.model.user.User;
import com.example.clientweb.data.model.user.UserContact;
import com.example.clientweb.repository.userRepository.UserRepository;
import com.example.clientweb.security.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserContactService userContactService;
    private final ModelMapper modelMapper;
    @Autowired
    public UserService(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder,
                       UserContactService userContactService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userContactService = userContactService;
        this.modelMapper = modelMapper;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public UserDto getUserDTO(String token) {
        String username = jwtUtil.validateTokenAndRetrieveClaim(token);
        return convertToUserDTO(getUserByUsername(username));
    }

    public boolean changePassword(String token, PasswordDto passwordDTO) {
        User user = userRepository.getByUsername(jwtUtil.validateTokenAndRetrieveClaim(token));

        if (passwordEncoder.matches(passwordDTO.getPasswordOld(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getPasswordNew()));
            save(user);
            return true;
        }
        return false;
    }

    public boolean changeMail(String token, String mail) {
        String username = jwtUtil.validateTokenAndRetrieveClaim(token);
        if (userContactService.findByMail(mail).isEmpty()) {
            UserContact userContact = userContactService.getByUsernameAndType(username, ContactType.MAIL);
            userContact.setContact(mail);
            userContactService.save(userContact);
            return true;
        }
        return false;
    }

    private UserDto convertToUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
