package com.example.clientweb.config;

import com.example.clientweb.security.CustomLogoutSuccessHandler;
import com.example.clientweb.security.JwtFilter;
import com.example.clientweb.service.userService.BlacklistService;
import com.example.clientweb.service.userService.ClientUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientUserDetailsService clientUserDetailsService;
    private final JwtFilter jwtFilter;
    private final BlacklistService blacklistService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(ClientUserDetailsService clientUserDetailsService, JwtFilter jwtFilter,
                          BlacklistService blacklistService, PasswordEncoder passwordEncoder) {
        this.clientUserDetailsService = clientUserDetailsService;
        this.jwtFilter = jwtFilter;
        this.blacklistService = blacklistService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(clientUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/profile/**").hasAnyRole("ADMIN", "USER", "MODERATOR")
                .antMatchers("/api/**", "/api/auth/login", "/api/registration", "/swagger-ui/*").permitAll()
                .and()
                .logout().logoutUrl("/api/logout").logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(blacklistService);
    }
}
