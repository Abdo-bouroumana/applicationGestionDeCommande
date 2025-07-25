package com.abderrazak.applicationGestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").permitAll() // Allow public access to these
                        .requestMatchers("/orders/**").permitAll()
                        .anyRequest().authenticated()              // Secure other endpoints
                )
                .httpBasic(Customizer.withDefaults())          // Use basic auth with default settings
                .csrf(AbstractHttpConfigurer::disable)                  // Disable CSRF for testing (like Postman)
                .build();
    }
}

