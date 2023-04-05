package com.example.todobackend.model;

import com.example.todobackend.dto.TodoStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
}
