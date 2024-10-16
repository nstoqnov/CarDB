package com.example.CarDB.Controller;

import com.example.CarDB.Model.User;
import com.example.CarDB.Service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    JdbcAggregateTemplate template;

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable){
        Page<User> users = userRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        return ResponseEntity.ok(users.getContent());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        Optional<User> foundUser = userRepo.findById(userId);
        if(foundUser.isPresent()){
            return ResponseEntity.ok(foundUser.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User requestUser, UriComponentsBuilder uriComponentsBuilder){
        User userSaved = template.insert(requestUser);
        URI locationOfNewUser = uriComponentsBuilder.path("/users/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> editUserById(@PathVariable Long userId, @RequestBody User requestedUser){
        Optional<User> foundUser = userRepo.findById(userId);
        if(foundUser.isPresent()){
            User updatedUser = template.update(requestedUser);
            return ResponseEntity.ok(updatedUser);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
