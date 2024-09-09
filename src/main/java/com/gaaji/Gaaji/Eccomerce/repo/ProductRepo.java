package com.gaaji.Gaaji.Eccomerce.repo;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo  extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}
