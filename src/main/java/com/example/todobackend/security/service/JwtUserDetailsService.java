package com.example.todobackend.security.service;

import com.example.todobackend.model.User;
import com.example.todobackend.repository.UserRepository;
import com.example.todobackend.security.dto.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        JwtUserDetails userDetails = new JwtUserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setPassword(user.getPassword());
        userDetails.setAdmin(user.isAdmin());
        userDetails.setAuthorities(new HashSet<>());
        return userDetails;
    }
}