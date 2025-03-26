package com.example.library_management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.library_management.model.User;
import com.example.library_management.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository _userRepository;

    public User Register(User user)
    {
        return _userRepository.save(user);
    }

    public Optional<User> findByUsername(String user)
    {
        return _userRepository.findByUsername(user);
    }

}
