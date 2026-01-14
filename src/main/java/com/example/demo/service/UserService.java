package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UsernameResponse;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
 
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getUser() {
        var res = userRepository.getUsers().stream()
        .map(user -> new UserResponse(user.getUsername(), user.getEmail()))
        .toList();

        return res;
    }

    public UsernameResponse getUserByUsername(String username) {
           var res = userRepository.getUsers().stream()
           .filter(user -> user.getUsername().contains(username))
           .map(user -> new UsernameResponse(user.getUsername()))
           .findFirst()
           .orElse(null);

           return res;
    }

    public String createUser(UserRequest request) {
       boolean exists = userRepository.getUsers().stream()
       .anyMatch(user -> user.getUsername().equalsIgnoreCase(request.getUsername()));

       if (exists) {
        return "Username already exists.";
       }

       if (request.getEmail() == null) {
        request.setEmail("defaultEmailNa@mail.com");
       }

       User user = new User(request.getUsername(), request.getEmail());
       userRepository.addUser(user);
       return "Create User Success.";
    }

    public String updateUser(String username, UserRequest body) {
        boolean exists = userRepository.getUsers().stream()
       .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

       if (!exists) {
        return "Username don't exists.";
       }

       User updateUser = new User(body.getUsername(), body.getEmail());
       userRepository.updateUser(username, updateUser);
       return "Update User Success.";
    }

    public String deleteUser(String username) {
       var res = userRepository.getUsers().stream()
           .filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase()))
           .findFirst()
           .orElse(null);

       if (res == null) {
        return "Username not exists.";
       }
        
        userRepository.removeUser(res);
        return "Update User Success.";
    }
}
