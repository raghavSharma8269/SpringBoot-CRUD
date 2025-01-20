package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.Query;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsService implements Query {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(GetAllProductsService.class);

    public GetAllProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Object sortBy) {

        logger.info("Executing " + getClass() + " input: " + sortBy);

        Sort sort = "price".equalsIgnoreCase(sortBy.toString())
                ? Sort.by(Sort.Direction.ASC, "price")
                : Sort.by(Sort.Direction.ASC, "name");

        List<Product> products = productRepository.findAll(sort);
        List<ProductDTO> productDTOS = products
                .stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok().body(productDTOS);
    }
}
