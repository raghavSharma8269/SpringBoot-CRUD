package com.example.SpringFinalProject.exceptions;

import lombok.Getter;

@Getter
public class ProfanityFilterApiResponse {

    private String original;
    private String censor;
    private boolean has_profanity;

}
