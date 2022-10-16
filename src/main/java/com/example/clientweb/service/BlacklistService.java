package com.example.clientweb.service;

import com.example.clientweb.repository.BlacklistRepository;
import com.example.clientweb.security.JWTUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final JWTUtil jwtUtil;

    public BlacklistService(BlacklistRepository blacklistRepository, JWTUtil jwtUtil) {
        this.blacklistRepository = blacklistRepository;
        this.jwtUtil = jwtUtil;
    }

    public void add(String token) {
        System.out.println(token);
        String key = jwtUtil.validateTokenAndRetrieveClaim(token);
        long exp = jwtUtil.extractExpiration(token);
        System.out.println(key);
        System.out.println(exp);
        blacklistRepository.add(key, exp);
    }

    public boolean findToken(String key){
        return blacklistRepository.findToken(key).isEmpty();
    }

    public void delete(String token) {
        blacklistRepository.delete(jwtUtil.validateTokenAndRetrieveClaim(token));
    }

}