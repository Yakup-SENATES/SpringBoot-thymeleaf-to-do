package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.todo")
public class ToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

}
