package com.example.bookapiapplication.controller;

import com.example.bookapiapplication.config.JwtService;
import com.example.bookapiapplication.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    // In-memory list to store registered users
    public static final List<User> users = new ArrayList<>();

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        users.add(user); // Saves the user to our list
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        // Find the user in our list
        User foundUser = users.stream()
                .filter(u -> u.getUsername().equals(loginRequest.getUsername()) &&
                        u.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElse(null);

        if (foundUser != null) {
            String token = jwtService.generateToken(foundUser); // Generate JWT
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}