package com.example.todo.controller;


import com.example.todo.model.ToDoItem;
import com.example.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/list")
    public List<ToDoItem> findAll() {
        return toDoService.findAll();
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody ToDoItem toDoItem) {
        toDoService.save(toDoItem);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody ToDoItem toDoItem) {
        toDoService.save(toDoItem);
    }

}
