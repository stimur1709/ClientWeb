package com.example.clientweb.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.clientweb.model.Role;
import com.example.clientweb.model.User;
import com.example.clientweb.model.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    @Value("${profile.jwt.key}")
    private String secret;

    @Value("${profile.jwt.expirationDay}")
    private long expirationDay;

    public String generateToken(User user) {

        Date expiration = Date.from(ZonedDateTime.now().plusDays(expirationDay).toInstant());

        List<Role> roles = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", user.getUsername())
                .withClaim("role", roles)
                .withIssuedAt(new Date())
                .withIssuer("safin")
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("safin")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    public long extractExpiration(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("safin")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getExpiresAt().getTime();
    }
}
