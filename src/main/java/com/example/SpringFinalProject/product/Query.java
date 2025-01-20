package com.example.SpringFinalProject.product;

import org.springframework.http.ResponseEntity;

public interface Query <I, O>{

    ResponseEntity<O> execute(I name);
}
