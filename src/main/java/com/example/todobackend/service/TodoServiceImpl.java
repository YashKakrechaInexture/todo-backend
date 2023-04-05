package com.example.todobackend.service;

import com.example.todobackend.dto.TodoStatus;
import com.example.todobackend.exception.ResourceNotFoundException;
import com.example.todobackend.model.Todo;
import com.example.todobackend.model.User;
import com.example.todobackend.repository.TodoRepository;
import com.example.todobackend.repository.UserRepository;
import com.example.todobackend.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:"+id));
    }

    @Override
    public Todo completeTodo(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:"+id));
        todo.setStatus(TodoStatus.COMPLETED);
        return todoRepository.save(todo);
    }

    @Override
    public Todo saveTodo(Todo todo, String authorization) {
        String email = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
        User user = userRepository.findByEmail(email);
        todo.setUser(user);
        todo.setStatus(TodoStatus.TODO);
        return todoRepository.save(todo);
    }
}
