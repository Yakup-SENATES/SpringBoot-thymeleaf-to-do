package com.example.todo.controller;


import com.example.todo.Dto.ToDoItemDto;
import com.example.todo.data.ToDoItemFormData;
import com.example.todo.model.ToDoItem;
import com.example.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/todo")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    //fetch all items in todo list
    @GetMapping
    public String ToDoIndex(Model model) {
        model.addAttribute("item", new ToDoItemFormData());
        model.addAttribute("todos",getToDoItems());
        model.addAttribute("numberOfActiveItems",getNumberOfActiveItems());
        model.addAttribute("totalNumberOfItems", toDoService.count());
        return "todo";
    }

    // it gives us to number of the active items in todo list
    private int getNumberOfActiveItems() {
        return toDoService.countAllByCompleted(false);
    }

    // its provide us list of the items in todo list
    private List<ToDoItemDto> getToDoItems() {
        return toDoService.findAll().stream()
                .map(toDoItem -> new ToDoItemDto(toDoItem.getId(),toDoItem.getTitle(),toDoItem.isCompleted()))
                .collect(Collectors.toList());
    }

    //add item to todo list
    @PostMapping
    public String addToDoItem(@ModelAttribute("item") ToDoItemFormData data) {
        toDoService.save(new ToDoItem(data.getTitle(), false));
        return "redirect:/todo";
    }

    // delete item from todo list
    @DeleteMapping("/{id}/delete")
    public String deleteToDoItem(@PathVariable("id") Long id) {
        toDoService.deleteById(id);
        return "redirect:/todo";
    }

    // update item in todo list
    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {

        ToDoItem toDoItem = toDoService.findById(id);
        toDoItem.setCompleted(!toDoItem.isCompleted());
        toDoService.save(toDoItem);
        return "redirect:/todo";
    }




}
