package com.example.clientweb.controller.userController;

import com.example.clientweb.data.dto.RegistrationDto;
import com.example.clientweb.service.userService.UserRegistrationService;
import com.example.clientweb.util.BindingResultResponse;
import com.example.clientweb.util.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserRegistrationService registrationService;
    private final RegistrationValidator registrationValidator;
    private final BindingResultResponse bindingResultResponse;

    @Autowired
    public RegistrationController(UserRegistrationService registrationService, RegistrationValidator registrationValidator,
                                  BindingResultResponse bindingResultResponse) {
        this.registrationService = registrationService;
        this.registrationValidator = registrationValidator;
        this.bindingResultResponse = bindingResultResponse;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> registration(@RequestBody @Valid RegistrationDto registrationDTO,
                                                            BindingResult bindingResult) {
        registrationValidator.validate(registrationDTO, bindingResult);

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.OK);

        return new ResponseEntity<>(registrationService.registrationUser(registrationDTO), HttpStatus.OK);
    }
}
