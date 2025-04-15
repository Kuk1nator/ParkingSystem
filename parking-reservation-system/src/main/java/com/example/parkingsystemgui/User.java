package com.example.parkingsystemgui;

import java.util.UUID;

public class User {
    private final String id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public enum Role {
        ADMIN,
        USER
    }

    public User(String username, String password, String email, Role role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    public boolean checkPassword(String passwordToCheck) {
       
        return this.password.equals(passwordToCheck);
    }

   
    public void setPassword(String password) {
      this.password = password;
    }
}