package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>(
        List.of(
            new User("Frame", "frame@mail.com"),
            new User("Froy", "froy@mail.com")
        )
    );

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        return;
    }

    public void updateUser(String username, User body) {
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.set(i, body);
                return;
            }
        }
    }

    public void removeUser(User user) {
        users.remove(user);
        return;
    }
}
