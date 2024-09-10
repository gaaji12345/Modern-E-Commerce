package com.gaaji.Gaaji.Eccomerce.service;/*  gaajiCode
    99
    10/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface ProductService {
    Responce createProduct(Long categoryId, MultipartFile image, String name, String description, BigDecimal price);
    Responce updateProduct(Long productId, Long categoryId, MultipartFile image, String name, String description, BigDecimal price);
    Responce deleteProduct(Long productId);
    Responce getProductById(Long productId);
    Responce getAllProducts();
    Responce getProductsByCategory(Long categoryId);
    Responce searchProduct(String searchValue);
}
