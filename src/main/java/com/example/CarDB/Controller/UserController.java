package com.example.CarDB.Controller;

import com.example.CarDB.Model.Authority;
import com.example.CarDB.Model.User;
import com.example.CarDB.Service.AuthorityRepo;
import com.example.CarDB.Service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    JdbcAggregateTemplate template;

    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;

    public UserController(UserRepo userRepo, AuthorityRepo authorityRepo) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
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

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody User requestUser, UriComponentsBuilder uriComponentsBuilder){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //List<Authority> authorities = new List<>(authentication.getAuthorities());
        User userSaved = template.insert(requestUser);

        Authority newAuthority = new Authority(userSaved.getUsername(),"ROLE_ADMIN");
        template.insert(newAuthority);
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

    @RequestMapping("/users/{userId}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long userId){
        Optional<User> isPresentUser = userRepo.findById(userId);
        if(isPresentUser.isPresent()){
            userRepo.deleteById(userId);
            return ResponseEntity.ok(userId);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
