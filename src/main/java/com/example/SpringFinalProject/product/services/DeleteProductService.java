package com.example.SpringFinalProject.product.services;

import com.example.SpringFinalProject.exceptions.ProductNotFoundException;
import com.example.SpringFinalProject.product.Command;
import com.example.SpringFinalProject.product.ProductRepository;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command <String, Void> {

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteProductService.class);

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {

        logger.info("Executing " + getClass() + " input: " + id);

        Optional<Product> deletedProduct = productRepository.findById(id);

        if (deletedProduct.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        }


            throw new ProductNotFoundException();
    }
}
