package com.example.SpringFinalProject.exceptions;

import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductValidator {

    private static final Logger logger = LoggerFactory.getLogger(ProductValidator.class);

    private ProductValidator(){

    }
    
    public static void execute(Product product){

        logger.info("Executing " + ProductValidator.class.getSimpleName() + " input: " + product);

        if(product.getName().isEmpty()){
            throw new ProductNotValidException(ExceptionMessages.NAME_REQUIRED.getMessage());
        }

        if(product.getDescription().length()<20){
            throw new ProductNotValidException(ExceptionMessages.DESCRIPTION_LENGTH.getMessage());
        }

        if (product.getManufacturer().isEmpty()){
            throw new ProductNotValidException(ExceptionMessages.MANUFACTURER_IS_NEEDED.getMessage());
        }

        if(product.getPrice()<=0){
            throw new ProductNotValidException(ExceptionMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }

        if (product.getCategory() == null){
            throw new ProductNotValidException(ExceptionMessages.CATEGORY_IS_NEEDED.getMessage());
        }

        if(product.getRegion() == null){
            throw new ProductNotValidException(ExceptionMessages.REGION_IS_NEEDED.getMessage());
        }

        if (!(product.getRegion().equals(Region.US) || product.getRegion().equals(Region.CANADA))){
            throw new ProductNotValidException(ExceptionMessages.REGION_NOT_SUPPORTED.getMessage());
        }


        if(ProfanityValidator.hasProfanity(product.getName(), product.getDescription())){
        throw new ProductNotValidException(ExceptionMessages.NO_PROFANITY.getMessage());
        }


    }
}
