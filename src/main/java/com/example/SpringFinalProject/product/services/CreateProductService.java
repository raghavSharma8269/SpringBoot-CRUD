package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.exceptions.ProductValidator;
import com.example.SpringFinalProject.product.Command;
import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(CreateProductService.class);


    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        logger.info("Executing " + getClass() + " input: " + product);

        ProductValidator.execute(product);

        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
