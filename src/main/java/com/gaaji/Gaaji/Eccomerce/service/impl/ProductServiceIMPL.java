package com.gaaji.Gaaji.Eccomerce.service.impl;/*  gaajiCode
    99
    10/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.ProductDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.entity.Category;
import com.gaaji.Gaaji.Eccomerce.entity.Product;
import com.gaaji.Gaaji.Eccomerce.exeption.NotFoundException;
import com.gaaji.Gaaji.Eccomerce.mapper.EntityDtoMapper;
import com.gaaji.Gaaji.Eccomerce.repo.CategoryRepo;
import com.gaaji.Gaaji.Eccomerce.repo.ProductRepo;
import com.gaaji.Gaaji.Eccomerce.service.AwsS3Service;
import com.gaaji.Gaaji.Eccomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceIMPL  implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;
    private final AwsS3Service awsS3Service;
    @Override
    public Responce createProduct(Long categoryId, MultipartFile image, String name, String description, BigDecimal price) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        String productImageUrl = awsS3Service.saveImageToS3(image);

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(productImageUrl);

        productRepo.save(product);
        return Responce.builder()
                .status(200)
                .message("Product successfully created")
                .build();
    }

    @Override
    public Responce updateProduct(Long productId, Long categoryId, MultipartFile image, String name, String description, BigDecimal price) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not Found"));

        Category category = null;
        String productImageUrl = null;

        if(categoryId != null ){
            category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        }
        if (image != null && !image.isEmpty()){
            productImageUrl = awsS3Service.saveImageToS3(image);
        }

        if (category != null) product.setCategory(category);
        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        if (description != null) product.setDescription(description);
        if (productImageUrl != null) product.setImageUrl(productImageUrl);

        productRepo.save(product);
        return Responce.builder()
                .status(200)
                .message("Product updated successfully")
                .build();
    }

    @Override
    public Responce deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not Found"));
        productRepo.delete(product);

        return Responce.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();
    }

    @Override
    public Responce getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not Found"));
        ProductDTO productDto = entityDtoMapper.mapProductToDtoBasic(product);

        return Responce.builder()
                .status(200)
                .product(productDto)
                .build();
    }

    @Override
    public Responce getAllProducts() {
        List<ProductDTO> productList = productRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());

        return Responce.builder()
                .status(200)
                .productList(productList)
                .build();
    }

    @Override
    public Responce getProductsByCategory(Long categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        if(products.isEmpty()){
            throw new NotFoundException("No Products found for this category");
        }
        List<ProductDTO> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());

        return Responce.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }

    @Override
    public Responce searchProduct(String searchValue) {
        List<Product> products = productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue);

        if (products.isEmpty()){
            throw new NotFoundException("No Products Found");
        }
        List<ProductDTO> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());


        return Responce.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }
}
