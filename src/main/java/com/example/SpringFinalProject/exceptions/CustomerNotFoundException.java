package com.example.SpringFinalProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(CustomerNotFoundException.class);
    public CustomerNotFoundException(){
        super(ExceptionMessages.USER_DOES_NOT_EXIST.getMessage());
        logger.error("Exception " + getClass() + " thrown");
    }

}
