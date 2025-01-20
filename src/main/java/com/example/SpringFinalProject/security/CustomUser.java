package com.example.SpringFinalProject.security;

import com.example.SpringFinalProject.security.jwt.Authority;
import com.example.SpringFinalProject.security.jwt.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    @Id
    @NotNull(message = "Username cannot be empty")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;



}
