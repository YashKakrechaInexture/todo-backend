package com.example.todobackend.service;

import com.example.todobackend.dto.TodoStatus;
import com.example.todobackend.model.Todo;

import java.util.List;
import java.util.Map;

public interface TodoService {
    List<Todo> getAll(String status);
    Todo getTodoById(Long id);
    Todo completeTodo(Long id);
    Todo saveTodo(Todo Todo, String authorization);
    Map<TodoStatus,Integer> getSummary();
}
