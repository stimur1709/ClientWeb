package com.example.clientweb.controller;

import com.example.clientweb.dto.RegistrationDTO;
import com.example.clientweb.model.User;
import com.example.clientweb.security.JWTUtil;
import com.example.clientweb.service.UserRegistrationService;
import com.example.clientweb.util.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserRegistrationService registrationService;
    private final RegistrationValidator registrationValidator;
    private final JWTUtil jwtUtil;

    @Autowired
    public RegistrationController(UserRegistrationService registrationService,
                                  RegistrationValidator registrationValidator, JWTUtil jwtUtil) {
        this.registrationService = registrationService;
        this.registrationValidator = registrationValidator;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> registration(@RequestBody @Valid RegistrationDTO registrationDTO,
                                                            BindingResult bindingResult) {
        registrationValidator.validate(registrationDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Map.of("message",
                    Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage())), HttpStatus.OK);
        }

        User user = registrationService.registrationUser(registrationDTO);
        return new ResponseEntity<>(Map.of("jwt-token", jwtUtil.generateToken(user)), HttpStatus.OK);
    }
}
