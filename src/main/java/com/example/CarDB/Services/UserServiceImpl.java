package com.example.CarDB.Services;

import com.example.CarDB.Models.Role;
import com.example.CarDB.Models.User;
import com.example.CarDB.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
    User user = new User();
    user.setUsername(registrationDTO.getUsername());
    user.setEmail(registrationDTO.getEmail());
    user.setPassword(registrationDTO.getPassword());
    Role role = roleRepo.findByName("USER");
    user.setRoles(Arrays.asList(role));
    userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByUsername(String un) {
        return userRepo.findByUsername(un);
    }
}
