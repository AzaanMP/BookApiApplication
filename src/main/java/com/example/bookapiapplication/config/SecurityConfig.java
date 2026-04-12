package com.example.bookapiapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Note: You may need to import your specific JwtAuthenticationFilter
// depending on which package it is currently sitting in.
// import com.example.bookapiapplication.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless REST APIs
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to register and login
                        .requestMatchers("/api/auth/**").permitAll()

                        // Allow anyone to view books
                        .requestMatchers(HttpMethod.GET, "/api/books/**").permitAll()

                        // ASSIGNMENT 2 REQUIREMENT: Only ADMIN can delete
                        .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")

                        // Require at least USER role to create, update, or patch books
                        .requestMatchers("/api/books/**").hasAnyRole("USER", "ADMIN")

                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )
                // Ensure session is stateless (standard for JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Add your existing JWT filter before the standard authentication filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}