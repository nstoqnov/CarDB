package com.example.CarDB.Services;

import com.example.CarDB.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String un);
}
