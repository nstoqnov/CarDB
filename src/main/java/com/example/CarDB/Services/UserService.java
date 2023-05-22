package com.example.CarDB.Services;

import com.example.CarDB.Models.User;
import com.example.CarDB.dto.RegistrationDTO;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    User findByEmail(String email);
    User findByUsername(String un);
}
