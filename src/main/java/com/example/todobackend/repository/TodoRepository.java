package com.example.todobackend.repository;

import com.example.todobackend.dto.TodoStatus;
import com.example.todobackend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> getTodosByStatusEquals(TodoStatus status);
    Integer countByStatusEquals(TodoStatus status);
}
