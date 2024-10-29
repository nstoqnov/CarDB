package com.example.CarDB.Controller;

import com.example.CarDB.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // Authentication logic here (use your user service)
        if (true) {
            String token = JwtUtil.generateToken(username);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

