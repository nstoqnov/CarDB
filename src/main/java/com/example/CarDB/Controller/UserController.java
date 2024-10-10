package com.example.CarDB.Controller;

import com.example.CarDB.Model.User;
import com.example.CarDB.Service.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable){
        Page<User> users = userRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        return ResponseEntity.ok(users.getContent());
    }

}
