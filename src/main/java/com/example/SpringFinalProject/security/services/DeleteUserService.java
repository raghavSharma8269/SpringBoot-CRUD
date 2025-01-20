package com.example.SpringFinalProject.security.services;

import com.example.SpringFinalProject.exceptions.CustomerNotFoundException;
import com.example.SpringFinalProject.product.Command;
import com.example.SpringFinalProject.security.CustomUser;
import com.example.SpringFinalProject.security.CustomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserService implements Command<String, Void> {

    Logger logger = LoggerFactory.getLogger(DeleteUserService.class);
    private final CustomUserRepository customUserRepository;

    public DeleteUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }


    @Override
    public ResponseEntity<Void> execute(String username) {

        logger.info("Executing" + getClass() + " input: " + username);

        Optional<CustomUser> optionalCustomUser = customUserRepository.findById(username);

        if (optionalCustomUser.isPresent()){
            customUserRepository.deleteById(username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new CustomerNotFoundException();
    }
}
