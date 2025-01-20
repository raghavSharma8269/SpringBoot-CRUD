package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.exceptions.ProductNotFoundException;
import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.Query;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductByIdService implements Query<String, ProductDTO> {

    private final ProductRepository productRepository;
    private final static Logger logger = LoggerFactory.getLogger(GetProductByIdService.class);

    public GetProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(String id) {

        logger.info("Executing " + getClass() + " input: " + id);

        Optional<Product> searchedProduct = productRepository.findById(id);

        if (searchedProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(searchedProduct.get()));
        }


        throw new ProductNotFoundException();
    }
}
