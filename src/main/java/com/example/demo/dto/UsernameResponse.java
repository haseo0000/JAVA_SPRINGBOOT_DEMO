package com.example.demo.dto;

public class UsernameResponse {
    private String username;
    private String message;

    public UsernameResponse(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
