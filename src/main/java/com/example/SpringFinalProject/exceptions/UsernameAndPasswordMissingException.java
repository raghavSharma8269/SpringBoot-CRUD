package com.example.SpringFinalProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAndPasswordMissingException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(UsernameAndPasswordMissingException.class);

    public UsernameAndPasswordMissingException(String message){
        super(message);

        logger.error("Exception " + getClass() + " thrown");
    }

}
