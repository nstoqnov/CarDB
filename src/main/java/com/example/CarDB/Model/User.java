package com.example.CarDB.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table("USERS")
public class User {
    @Id
    private Long id;
    private String username;
    private String role;
    private String password;
    private String email;
    private boolean enabled;

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        this.password = "{bcrypt}" + encodedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public User(Long id, String username, String role, String password,String email, boolean enabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        String encodedPass = new BCryptPasswordEncoder().encode(password);
        this.password = "{bcrypt}" + encodedPass;
        this.email = email;
        this.enabled = enabled;
    }

    public User() {
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


}
