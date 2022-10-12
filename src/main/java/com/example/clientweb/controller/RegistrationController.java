package com.example.clientweb.controller;

import com.example.clientweb.dto.PersonDTO;
import com.example.clientweb.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserRegistrationService registrationService;

    public RegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping()
    public ResponseEntity<String> registration(@RequestBody PersonDTO personDTO) {
        registrationService.registrationUser(personDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
