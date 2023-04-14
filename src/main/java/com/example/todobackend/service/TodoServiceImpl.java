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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Todo> getAll(String status) {
        if(status==null) {
            return todoRepository.findAll();
        }
        return todoRepository.getTodosByStatusEquals(TodoStatus.valueOf(status));
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
        if(todo.getId()==0) {
            String email = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
            User user = userRepository.findByEmail(email);
            todo.setUser(user);
            todo.setStatus(TodoStatus.TODO);
        }
        return todoRepository.save(todo);
    }

    @Override
    public Map<TodoStatus,Integer> getSummary(){
        Map<TodoStatus, Integer> summary = new EnumMap<>(TodoStatus.class);
        for(TodoStatus status : TodoStatus.values()){
            summary.put(status,todoRepository.countByStatusEquals(status));
        }
        return summary;
    }
}
