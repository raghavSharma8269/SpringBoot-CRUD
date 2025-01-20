package com.example.SpringFinalProject.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 chars or longer"),
    PRICE_CANNOT_BE_NEGATIVE("Price must be greater than zero"),
    MANUFACTURER_IS_NEEDED("Manufacturer is required"),
    NO_PROFANITY("Profanity is not allowed"),
    PROFANITY_FILTER_DOWN("Profanity filter is down"),
    CATEGORY_IS_NEEDED("Category is needed"),
    REGION_IS_NEEDED("Region is needed"),
    REGION_NOT_SUPPORTED("Region is not supported"),
    USERNAME_NOT_AVAILABLE("This username is already taken"),
    USERNAME_IS_NEEDED("Username is needed"),
    PASSWORD_IS_NEEDED("Password is needed"),
    USER_DOES_NOT_EXIST("This user does not exist");


    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

}
