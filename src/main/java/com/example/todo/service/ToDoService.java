package com.example.todo.service;

import com.example.todo.model.ToDoItem;
import com.example.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public List<ToDoItem> findAll() {
        return toDoRepository.findAll();
    }

    public ToDoItem save(ToDoItem toDoItem) {
        return toDoRepository.save(toDoItem);
    }

    public void deleteById(Long id) {
        toDoRepository.deleteById(id);
    }

    public ToDoItem findById(Long id) {
        return toDoRepository.findById(id).orElseThrow();
    }

}


