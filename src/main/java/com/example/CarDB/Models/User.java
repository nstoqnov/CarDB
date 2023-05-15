package com.example.CarDB.Models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;

    private String username;

    private String passwordHash;

    private String fullName;

    private Set<Trip> posts = new HashSet<>();

    public User(Long id,String username, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Trip> getPosts() {
        return posts;
    }

    public void setPosts(Set<Trip> posts) {
        this.posts = posts;
    }
}
