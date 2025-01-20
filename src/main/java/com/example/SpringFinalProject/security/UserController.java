package com.example.SpringFinalProject.security;

import com.example.SpringFinalProject.security.services.CreateNewUserService;
import com.example.SpringFinalProject.security.services.DeleteUserService;
import com.example.SpringFinalProject.security.services.GetAllUsersService;
import com.example.SpringFinalProject.security.services.GetUserByUsername;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final CreateNewUserService createNewUserService;
    private final DeleteUserService deleteUserService;
    private final GetAllUsersService getAllUsersService;
    private final GetUserByUsername getUserByUsername;

    public UserController(CreateNewUserService createNewUserService, DeleteUserService deleteUserService, GetAllUsersService getAllUsersService, GetUserByUsername getUserByUsername) {
        this.createNewUserService = createNewUserService;
        this.deleteUserService = deleteUserService;
        this.getAllUsersService = getAllUsersService;
        this.getUserByUsername = getUserByUsername;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser){
        return createNewUserService.execute(customUser);
    }

    @DeleteMapping("/deleteuser/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        return deleteUserService.execute(username);
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<CustomUser>> getAllUsers(){
        return getAllUsersService.execute(null);
    }

    @GetMapping("/getuser/{username}")
    public ResponseEntity<CustomUserDTO> getUserByUsername(@PathVariable String username){
        return getUserByUsername.execute(username);
    }



}
