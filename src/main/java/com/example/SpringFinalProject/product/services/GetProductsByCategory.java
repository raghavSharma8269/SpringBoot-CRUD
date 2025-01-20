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
public class GetProductsByCategory implements Query<String, List<ProductDTO>> {

    ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(GetProductsByCategory.class);

    public GetProductsByCategory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String category) {

        logger.info("Executing " + getClass() + " input: " + category);

        return ResponseEntity.ok(productRepository.findByCategory(category)
                .stream()
                .limit(10)
                .map(ProductDTO::new)
                .toList());
    }
}
