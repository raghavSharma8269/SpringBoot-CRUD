package com.example.SpringFinalProject.security;

import com.example.SpringFinalProject.security.jwt.Authority;
import lombok.Data;

@Data
public class CustomUserDTO {
    private String username;
    private String password;
    private Authority authority;


    public CustomUserDTO(CustomUser customUser){
        this.username = customUser.getUsername();
        this.password = customUser.getPassword();
        this.authority = customUser.getAuthority();
    }
}
