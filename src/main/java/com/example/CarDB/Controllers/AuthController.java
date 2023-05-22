package com.example.CarDB.Controllers;

import com.example.CarDB.Models.User;
import com.example.CarDB.Services.UserService;
import com.example.CarDB.dto.RegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDTO user, BindingResult result, Model model){
        User existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            result.rejectValue("email","There is already a user with this email/username!");
        }
        User existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()){
            result.rejectValue("username","There is already a user with this email/username!");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/trips?success";
    }
}
