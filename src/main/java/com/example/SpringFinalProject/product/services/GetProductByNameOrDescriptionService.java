package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.Query;
import com.example.SpringFinalProject.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetProductByNameOrDescriptionService implements Query<String, List<ProductDTO>> {

    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(GetProductByNameOrDescriptionService.class);

    public GetProductByNameOrDescriptionService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {

        logger.info("Executing " + getClass() + " input: " + name);

        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(name)
                .stream()
                .limit(10)
                .map(ProductDTO::new)
                .toList());
    }
}
