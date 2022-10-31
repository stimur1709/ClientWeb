package com.example.clientweb.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.clientweb.service.userService.BlacklistService;
import com.example.clientweb.service.userService.ClientUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final ClientUserDetailsService clientUserDetailsService;
    private final BlacklistService blacklistService;

    public JwtFilter(JWTUtil jwtUtil, ClientUserDetailsService clientUserDetailsService,
                     BlacklistService blacklistService) {
        this.jwtUtil = jwtUtil;
        this.clientUserDetailsService = clientUserDetailsService;
        this.blacklistService = blacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT Token in Bearer Header");
                return;
            } else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);

                    if (blacklistService.findToken(username)) {
                        UserDetails userDetails = clientUserDetailsService.loadUserByUsername(username);

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails,
                                        userDetails.getPassword(), userDetails.getAuthorities());

                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                } catch (JWTVerificationException exc) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,
                            "Invalid JWT Token");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
