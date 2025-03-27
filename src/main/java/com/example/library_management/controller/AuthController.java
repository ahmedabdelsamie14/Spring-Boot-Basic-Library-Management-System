package com.example.library_management.controller;

import com.example.library_management.model.Role;
import com.example.library_management.model.User;
import com.example.library_management.repository.UserRepository;
import com.example.library_management.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Allow only one admin
        if (user.getUsername().equals("admin")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs in a user and returns a JWT token")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return "Invalid credentials!";
    }
}
