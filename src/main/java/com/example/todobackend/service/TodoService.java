package com.example.todobackend.service;

import com.example.todobackend.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();
    Todo getTodoById(Long id);
    Todo completeTodo(Long id);
    Todo saveTodo(Todo Todo, String authorization);
}
