package com.example.clientweb.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class BindingResultResponse {

    public Map<String, Object> getMessage(BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", false);
        for (FieldError fieldError : bindingResult.getFieldErrors())
            response.put(fieldError.getField(), fieldError.getDefaultMessage());

        return response;
    }

}
