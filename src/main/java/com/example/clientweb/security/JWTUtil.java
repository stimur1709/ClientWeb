package com.example.clientweb.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.key}")
    private String secret;

    @Value("${jwt.expirationDay}")
    private long expirationDay;

    public String generateToken(String username) {

        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(expirationDay).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
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

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        return authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")
                ? authHeader.substring(7) : null;
    }
}
