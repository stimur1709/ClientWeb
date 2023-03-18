package com.example.clientweb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Component
public class MessageLocale {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    @Autowired
    public MessageLocale(MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.request = request;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localeResolver.resolveLocale(request));
    }
}
