package com.example.todo.service;

import com.example.todo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);
    User findByUserName(String userName);

}
