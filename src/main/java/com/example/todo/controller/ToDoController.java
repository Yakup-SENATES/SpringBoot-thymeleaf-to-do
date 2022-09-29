package com.example.todo.controller;


import com.example.todo.Dto.ToDoItemDto;
import com.example.todo.data.ListFilter;
import com.example.todo.data.ToDoItemFormData;
import com.example.todo.model.ToDoItem;
import com.example.todo.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/todo")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    //fetch all items in todo list
    @GetMapping
    @ApiOperation(value = "Get all items in todo list")
    public String index(Model model) {
       addAttributesForIndex(model,ListFilter.ALL);
        return "todo";
    }

    // get active items in todo list
    @GetMapping("/active")
    @ApiOperation(value = "Get active items in todo list")
    public String indexActive(Model model) {
        addAttributesForIndex(model,ListFilter.ACTIVE);
        return "todo";
    }

    // get completed items in todo list
    @ApiOperation(value = "Get completed items in todo list")
    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributesForIndex(model,ListFilter.COMPLETED);
        return "todo";
    }

    @DeleteMapping("/completed")
    @ApiOperation(value = "Delete completed items in todo list")
    public String deleteCompleted() {
        List<ToDoItem> completedItems = toDoService.findAllByCompleted(true);
        for (ToDoItem item : completedItems) {
            toDoService.deleteById(item.getId());
        }
        return "redirect:/todo";
    }

    public void addAttributesForIndex(Model model, ListFilter listFilter) {
        model.addAttribute("item", new ToDoItemFormData());
        model.addAttribute("todos",getToDoItems(listFilter));
        model.addAttribute("numberOfActiveItems",getNumberOfActiveItems());
        model.addAttribute("totalNumberOfItems", toDoService.count());
        model.addAttribute("filter",listFilter);
        model.addAttribute("numberOfCompletedItems", getNumberOfCompletedItems());
    }

    // it will return all completed items in todo list
    private int getNumberOfCompletedItems() {
        return toDoService.countAllByCompleted(true);
    }


    // it gives us to number of the active items in todo list
    private int getNumberOfActiveItems() {
        return toDoService.countAllByCompleted(false);
    }

    // its provide us list of the items in todo list
    private List<ToDoItemDto> getToDoItems(ListFilter filter) {
        return switch (filter) {
            case ALL -> convertToDto(toDoService.findAll());
            case ACTIVE -> convertToDto(toDoService.findAllByCompleted(false));
            case COMPLETED -> convertToDto(toDoService.findAllByCompleted(true));
        };
    }

    // convert to do item to to do item dto
    private List<ToDoItemDto> convertToDto(List<ToDoItem> toDoItems) {
        return toDoItems
                .stream()
                .map(todoItem -> new ToDoItemDto(todoItem.getId(),
                        todoItem.getTitle(),
                        todoItem.isCompleted()))
                .collect(Collectors.toList());
    }


    //add item to todo list
    @PostMapping
    @ApiOperation(value = "Add item to todo list")
    public String addToDoItem(@ModelAttribute("item") ToDoItemFormData data) {
        toDoService.save(new ToDoItem(data.getTitle(), false));
        return "redirect:/todo";
    }

    // delete item from todo list
    @DeleteMapping("/{id}/delete")
    @ApiOperation(value = "Delete item from todo list")
    public String deleteToDoItem(@PathVariable("id") Long id) {
        toDoService.deleteById(id);
        return "redirect:/todo";
    }

    // update item in todo list
    @PutMapping("/{id}/toggle")
    @ApiOperation(value = "Update item in todo list")
    public String toggleSelection(@PathVariable("id") Long id) {

        ToDoItem toDoItem = toDoService.findById(id);
        toDoItem.setCompleted(!toDoItem.isCompleted());
        toDoService.save(toDoItem);
        return "redirect:/todo";
    }

    // Select All
    @PutMapping("/toggle-all")
    @ApiOperation(value = "Update all items in todo list")
    public String toggleAllSelection() {
        List<ToDoItem> toDoItems = toDoService.findAll();
        for (ToDoItem item : toDoItems) {
            item.setCompleted(!item.isCompleted());
            toDoService.save(item);
        }
        return "redirect:/todo";
    }

    @GetMapping("/test")
    public String test() {
        return "index";
    }

}
