package com.example.SpringFinalProject.security.services;

import com.example.SpringFinalProject.product.Query;
import com.example.SpringFinalProject.security.CustomUser;
import com.example.SpringFinalProject.security.CustomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersService implements Query<Void, List<CustomUser>>{
    private final CustomUserRepository customUserRepository;
    Logger logger = LoggerFactory.getLogger(GetAllUsersService.class);

    public GetAllUsersService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public ResponseEntity<List<CustomUser>> execute(Void name) {

        logger.info("Executing " + getClass());

        List<CustomUser> users = customUserRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
