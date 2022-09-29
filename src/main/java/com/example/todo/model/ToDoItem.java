package com.example.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ApiModel(value = "ToDo Item",description = "All details about the ToDoItem. ")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated ToDoItem ID")
    private Long id;

    @ApiModelProperty(notes = "The ToDoItem description")
    private String title;
    @ApiModelProperty(notes = "The ToDoItem status")
    private boolean completed;


    public ToDoItem(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public ToDoItem() {
    }


    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
