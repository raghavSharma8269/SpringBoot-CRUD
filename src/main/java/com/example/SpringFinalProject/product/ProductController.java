package com.example.SpringFinalProject.product;

import com.example.SpringFinalProject.product.model.Category;
import com.example.SpringFinalProject.product.model.Product;
import com.example.SpringFinalProject.product.model.ProductDTO;
import com.example.SpringFinalProject.product.model.UpdateProductCommand;
import com.example.SpringFinalProject.product.services.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {

    private final GetAllProductsService getAllProductsService;
    private final CreateProductService createProductService;
    private final GetProductByIdService getProductByIdService;
    private final DeleteProductService deleteProductService;
    private final UpdateProductService updateProductService;
    private final GetProductByNameOrDescriptionService getProductByNameOrDescriptionService;
    private final GetProductsByCategory getProductsByCategory;

    public ProductController(GetAllProductsService getAllProductsService, CreateProductService createProductService, GetProductByIdService getProductByIdService, DeleteProductService deleteProductService, UpdateProductService updateProductService, GetProductByNameOrDescriptionService getProductByNameOrDescriptionService, GetProductsByCategory getProductsByCategory) {
        this.getAllProductsService = getAllProductsService;
        this.createProductService = createProductService;
        this.getProductByIdService = getProductByIdService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.getProductByNameOrDescriptionService = getProductByNameOrDescriptionService;
        this.getProductsByCategory = getProductsByCategory;
    }


    @PostMapping("/createproduct")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody Product product){
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "name") String sortBy){
        return getAllProductsService.execute(sortBy);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable String id){
        return getProductByIdService.execute(id);
    }

    @DeleteMapping("/deleteproduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        return deleteProductService.execute(id);
    }

    @PutMapping("/updateproduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ProductDTO> updateProduct (@PathVariable String id, @RequestBody Product product){
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @GetMapping("/product/searchByNameOrDescription")
    public ResponseEntity<List<ProductDTO>> getProductsByNameOrDescription(@RequestParam String nameOrDesc){
        return getProductByNameOrDescriptionService.execute(nameOrDesc);
    }

    @GetMapping("/product/searchbycategory")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam String category){
        return getProductsByCategory.execute(category);
    }
}
