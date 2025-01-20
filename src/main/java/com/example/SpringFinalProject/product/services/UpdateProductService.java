package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.exceptions.ProductNotFoundException;
import com.example.SpringFinalProject.exceptions.ProductValidator;
import com.example.SpringFinalProject.product.Command;
import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import com.example.SpringFinalProject.product.model.UpdateProductCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(UpdateProductService.class);

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "productCache", key = "#command.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {

        logger.info("Executing " + getClass() + " input: " + command);


        Optional<Product> updatedProduct = productRepository.findById(command.getId());

        if (updatedProduct.isPresent()){
            Product product = command.getProduct();
            product.setUpdatedAt(LocalDateTime.now());
            product.setId(command.getId());
            ProductValidator.execute(product);
            productRepository.save(product);

            return ResponseEntity.ok(new ProductDTO(product));
        }


        throw new ProductNotFoundException();
    }
}
