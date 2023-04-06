package com.example.todobackend.controller;

import com.example.todobackend.dto.TodoStatus;
import com.example.todobackend.model.Todo;
import com.example.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(@RequestParam(required = false) String status) {
        return todoService.getAll(status);
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

    @GetMapping("/summary")
    public Map<TodoStatus,Integer> getTodoSummary() {
        return todoService.getSummary();
    }
}
