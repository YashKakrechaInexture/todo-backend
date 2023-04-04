package com.example.todobackend.service;

import com.example.todobackend.dto.UserDto;
import com.example.todobackend.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(Long id);
    User saveUser(UserDto userDto, String authorization);
}
