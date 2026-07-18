package com.example.EComApp.repository.product;

import com.example.EComApp.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCategoryIdAndIsActiveTrue(Long id);
}
