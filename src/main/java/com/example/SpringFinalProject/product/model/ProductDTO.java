package com.example.SpringFinalProject.product.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String manufacturer;
    private double price;
    private Region region;
    private Category category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.region = product.getRegion();
    }
}
