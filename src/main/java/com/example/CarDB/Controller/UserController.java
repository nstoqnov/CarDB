package com.example.CarDB.Controller;

import com.example.CarDB.Service.UserRepo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    
}
