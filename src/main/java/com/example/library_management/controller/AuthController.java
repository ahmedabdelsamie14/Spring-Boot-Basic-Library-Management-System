package com.example.library_management.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_management.model.User;
import com.example.library_management.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    

    private UserService _userService;

    @PostMapping("/register")
    public String Register(@RequestBody User user)
    {
        _userService.Register(user);
        return "User registered successfully";
    }
}
