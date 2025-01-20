package com.example.SpringFinalProject.security.services;

import com.example.SpringFinalProject.exceptions.UsernameAndPasswordMissingValidator;
import com.example.SpringFinalProject.exceptions.UsernameNotAvailableException;
import com.example.SpringFinalProject.product.Command;
import com.example.SpringFinalProject.security.CustomUser;
import com.example.SpringFinalProject.security.CustomUserRepository;
import com.example.SpringFinalProject.security.jwt.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserService implements Command<CustomUser, String> {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepository customUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateNewUserService.class);

    public CreateNewUserService(PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = customUserRepository;
    }

    @Override
    public ResponseEntity<String> execute(CustomUser customUser) {

        logger.info("Executing " + getClass() + " input: " + customUser);

        Optional<CustomUser> optionalCustomUser = customUserRepository.findById(customUser.getUsername());

        if (optionalCustomUser.isEmpty()){
            UsernameAndPasswordMissingValidator.execute(customUser);
            customUser.setAuthority(Authority.USER);

            customUserRepository.save(new CustomUser(
                    customUser.getUsername(),
                    passwordEncoder.encode(customUser.getPassword()),
                    customUser.getAuthority()
            ));
            return ResponseEntity.ok().body("User Successfully Created");
        }

        throw new UsernameNotAvailableException();
    }
}
