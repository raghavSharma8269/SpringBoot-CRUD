package com.example.SpringFinalProject.exceptions;

import com.example.SpringFinalProject.security.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsernameAndPasswordMissingValidator {

    private static final Logger logger = LoggerFactory.getLogger(UsernameAndPasswordMissingValidator.class);


    public static void execute (CustomUser customUser){

        if(customUser.getUsername().isEmpty()){
            throw new UsernameAndPasswordMissingException(ExceptionMessages.USERNAME_IS_NEEDED.getMessage());
        }

        if (customUser.getPassword().isEmpty()){
            throw new UsernameAndPasswordMissingException(ExceptionMessages.PASSWORD_IS_NEEDED.getMessage());
        }

        logger.info("Executing " + UsernameAndPasswordMissingValidator.class.getSimpleName() + " input: " + customUser);
    }

}
