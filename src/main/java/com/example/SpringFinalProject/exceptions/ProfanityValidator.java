package com.example.SpringFinalProject.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ProfanityValidator {

    private static final String API_KEY = "z/04PpoQqXaY7vOKvBEjnQ==DTPYxIJRIeAbOe8l";
    private static final String url = "https://api.api-ninjas.com/v1/profanityfilter?text=";
    private static final Logger logger = LoggerFactory.getLogger(ProfanityValidator.class);

    public static boolean hasProfanity(String name, String description){

        logger.info("Executing " + ProfanityValidator.class.getSimpleName() + " name: " + name + ", description: " + description);


        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", API_KEY);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ProfanityFilterApiResponse> nameResponseEntity = restTemplate.exchange(url + name,
                    HttpMethod.GET,
                    entity, ProfanityFilterApiResponse.class);

            ResponseEntity<ProfanityFilterApiResponse> descriptionResponseEntity = restTemplate.exchange(url + description,
                    HttpMethod.GET,
                    entity, ProfanityFilterApiResponse.class);


            return (nameResponseEntity.getBody().isHas_profanity() || descriptionResponseEntity.getBody().isHas_profanity());
        } catch (RestClientException e) {
            throw new ProductNotValidException(ExceptionMessages.PROFANITY_FILTER_DOWN.getMessage());
        }

    }

}
