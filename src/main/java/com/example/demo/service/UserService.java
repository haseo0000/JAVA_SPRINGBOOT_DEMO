package com.example.demo.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        var res = userRepository.findAll().stream()
        .map(user -> new UserResponse(
            user.getUsername(),
            user.getEmail(),
            user.getAge())
        )
        .toList();

        return res;
    }

    public UsernameResponse getUserByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);

        if (user == null) {
            return new UsernameResponse(null, "Username doesn't exist.");
        }

        return new UsernameResponse(user.getUsername(), "");
    }

    public String createUser(UserRequest request) {
       boolean exists = userRepository.existsByUsernameIgnoreCase(request.getUsername());

       if (exists) {
        return "Username already exists.";
       }

       User user = new User(request.getUsername(), request.getEmail(), request.getAge());
       userRepository.save(user);
       return "Create User Success.";
    }

    public String updateUser(String username, UserRequest body) {
    //     boolean exists = userRepository.findAll().stream()
    //    .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

    //    if (!exists) {
    //     return "Username don't exists.";
    //    }

    //    User updateUser = new User(body.getUsername(), body.getEmail());
    //    userRepository.updateUser(username, updateUser);
    //    return "Update User Success.";

    

        User user = userRepository.findByUsernameIgnoreCase(username);

        if (user == null) {
            return "Username doesn't exist.";
        }          

        // update fields
        user.setUsername(body.getUsername());
        user.setEmail(body.getEmail());
        user.setAge(body.getAge());

        // No need to call save() explicitly (but ok if you do)
        userRepository.save(user);

        return "Update User Success.";
    }

    public String deleteUser(String username) {
    //    var res = userRepository.findAll().stream()
    //        .filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase()))
    //        .findFirst()
    //        .orElse(null);
    
       User user = userRepository.findByUsernameIgnoreCase(username);

       if (user == null) {
        return "Username not exists.";
       }
       
       userRepository.delete(user);
       return "Delete User Success.";
    }
}
