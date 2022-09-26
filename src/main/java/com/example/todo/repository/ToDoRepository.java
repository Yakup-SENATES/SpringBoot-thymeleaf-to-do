package com.example.todo.repository;

import com.example.todo.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoItem, Long> {


}
