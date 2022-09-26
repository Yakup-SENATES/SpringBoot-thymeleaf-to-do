package com.example.todo.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@RequiredArgsConstructor
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;
}
