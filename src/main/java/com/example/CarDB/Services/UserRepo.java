package com.example.CarDB.Services;

import com.example.CarDB.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String un);

    UserEntity findFirstByUsername(String un);
}
