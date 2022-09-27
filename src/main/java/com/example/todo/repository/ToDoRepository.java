package com.example.todo.repository;

import com.example.todo.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoItem, Long> {


    int countAllByCompleted(boolean completed);

    List<ToDoItem> findAllByCompleted(boolean completed);
}
