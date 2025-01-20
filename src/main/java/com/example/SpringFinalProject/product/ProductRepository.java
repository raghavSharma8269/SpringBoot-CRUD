package com.example.SpringFinalProject.product;

import com.example.SpringFinalProject.product.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE  %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword")String name);

    @Query("SELECT p from Product p WHERE p.category.value = :categoryValue")
    List<Product> findByCategory(@Param("categoryValue") String categoryValue);

    List<Product> findAll(Sort sort);
}
