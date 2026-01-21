package com.example.demo.dto;

public class UserResponse {
    private String username;
    private String email;
    private int age;

    public UserResponse(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
