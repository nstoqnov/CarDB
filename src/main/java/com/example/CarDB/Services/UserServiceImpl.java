package com.example.CarDB.Services;

import com.example.CarDB.Models.Role;
import com.example.CarDB.Models.UserEntity;
import com.example.CarDB.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
    UserEntity user = new UserEntity();
    user.setUsername(registrationDTO.getUsername());
    user.setEmail(registrationDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
    Role role = roleRepo.findByName("USER");
    user.setRoles(Arrays.asList(role));
    userRepo.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String un) {
        return userRepo.findByUsername(un);
    }
}
