package com.example.todobackend.controller;

import com.example.todobackend.dto.UserDto;
import com.example.todobackend.model.User;
import com.example.todobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody UserDto userDto,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(userService.saveUser(userDto,authorization));
    }
}
