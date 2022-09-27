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

    public void save(ToDoItem toDoItem) {
        toDoRepository.save(toDoItem);
    }

    public void deleteById(Long id) {
        toDoRepository.deleteById(id);
    }

    public ToDoItem findById(Long id) {
        return toDoRepository.findById(id).orElseThrow();
    }

    public Object count() {
        return toDoRepository.count();
    }


    public int countAllByCompleted(boolean completed) {
        return toDoRepository.countAllByCompleted(completed);
    }

    public List<ToDoItem> findAllByCompleted(boolean completed) {
        return toDoRepository.findAllByCompleted(completed);
    }
}


