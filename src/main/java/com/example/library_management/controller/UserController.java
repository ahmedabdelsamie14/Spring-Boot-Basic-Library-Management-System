package com.example.library_management.controller;

import com.example.library_management.model.User;
import com.example.library_management.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get all users", description = "Fetches a list of all registered users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "Get user by ID", description = "Fetches a specific user by ID")
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully";
        }
        return "User not found";
    }

    @GetMapping("/me")
    @Operation(summary = "Get my details", description = "Fetches the details of the logged-in user")
    public User getMyDetails(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
