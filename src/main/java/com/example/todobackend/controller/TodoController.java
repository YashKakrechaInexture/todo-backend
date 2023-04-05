package com.example.todobackend.controller;

import com.example.todobackend.model.Todo;
import com.example.todobackend.model.User;
import com.example.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() throws InterruptedException {
        return todoService.getAll();
    }

    @PostMapping("{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Todo> completeTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.completeTodo(id));
    }

    @PostMapping
    public ResponseEntity<Todo> createOrUpdateTodo(@RequestBody Todo todo,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(todoService.saveTodo(todo,authorization));
    }
}
