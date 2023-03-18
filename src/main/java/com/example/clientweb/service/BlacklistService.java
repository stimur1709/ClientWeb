package com.example.clientweb.service;

import com.example.clientweb.repository.BlacklistRepository;
import com.example.clientweb.security.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final JWTUtil jwtUtil;

    public BlacklistService(BlacklistRepository blacklistRepository, JWTUtil jwtUtil) {
        this.blacklistRepository = blacklistRepository;
        this.jwtUtil = jwtUtil;
    }

    public void add(String token) {
        String key = jwtUtil.validateTokenAndRetrieveClaim(token);
        Date now = new Date();
        blacklistRepository.add(key, jwtUtil.extractExpiration(token) - now.getTime());
    }

    public boolean findToken(String username) {
        return blacklistRepository.findToken(username).isEmpty();
    }

    public void delete(String username) {
        blacklistRepository.delete(username);
    }

}