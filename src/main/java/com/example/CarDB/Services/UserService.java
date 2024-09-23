package com.example.CarDB.Services;

import com.example.CarDB.Models.UserEntity;
import com.example.CarDB.dto.RegistrationDTO;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    UserEntity findByEmail(String email);
    UserEntity findByUsername(String un);

}
