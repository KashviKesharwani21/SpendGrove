package com.spendgrove.spendgrove.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // tells Spring this class defines Beans via @Bean methods
public class SecurityConfig {

    @Bean  // registers the return value as a Spring-managed Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF protection is for browser form submissions; not relevant for a stateless JSON API tested via Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // TEMPORARY: allow every request through, no login required
                );
        return http.build();
    }
}