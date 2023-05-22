package com.example.CarDB.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDTO {

    private Long Id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
