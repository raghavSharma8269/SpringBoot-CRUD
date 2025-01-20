package com.example.SpringFinalProject.product;

import org.springframework.http.ResponseEntity;

public interface Command <I,O>{

    ResponseEntity<O> execute (I input);
}
