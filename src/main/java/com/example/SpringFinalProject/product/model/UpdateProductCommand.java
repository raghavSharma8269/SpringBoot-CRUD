package com.example.SpringFinalProject.product.model;

import lombok.Getter;

@Getter
public class UpdateProductCommand {

    private String id;
    private Product product;

    public UpdateProductCommand(String id, Product product) {
        this.id = id;
        this.product = product;
    }
}
