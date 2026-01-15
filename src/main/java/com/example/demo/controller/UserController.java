package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UsernameResponse;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.getUser();
    }

    @GetMapping("/users/{username}")
    public UsernameResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody UserRequest user) {
        return userService.createUser(user);
    }

    @PostMapping("/users/update/{username}")
    public String updateUser(@PathVariable String username, @RequestBody UserRequest user) {
        return userService.updateUser(username, user);
    }

    @PostMapping("/users/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }
}