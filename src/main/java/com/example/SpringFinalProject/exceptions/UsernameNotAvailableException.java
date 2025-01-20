package com.example.SpringFinalProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameNotAvailableException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(UsernameNotAvailableException.class);

    public UsernameNotAvailableException(){
        super(ExceptionMessages.USERNAME_NOT_AVAILABLE.getMessage());
        logger.error("Exception " + getClass() + " thrown");
    }
}
