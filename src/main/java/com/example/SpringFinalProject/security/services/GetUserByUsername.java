package com.example.SpringFinalProject.security.services;

import com.example.SpringFinalProject.exceptions.CustomerNotFoundException;
import com.example.SpringFinalProject.product.Query;
import com.example.SpringFinalProject.security.CustomUser;
import com.example.SpringFinalProject.security.CustomUserDTO;
import com.example.SpringFinalProject.security.CustomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByUsername implements Query<String, CustomUserDTO> {

    private final CustomUserRepository customUserRepository;
    private final Logger logger = LoggerFactory.getLogger(GetUserByUsername.class);

    public GetUserByUsername(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public ResponseEntity<CustomUserDTO> execute(String username) {
        logger.info("Executing " + getClass() + " input: " + username);

        Optional<CustomUser> optionalCustomUser = customUserRepository.findById(username);

        if (optionalCustomUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new CustomUserDTO(optionalCustomUser.get()));
        }

        throw new CustomerNotFoundException();
    }
}
