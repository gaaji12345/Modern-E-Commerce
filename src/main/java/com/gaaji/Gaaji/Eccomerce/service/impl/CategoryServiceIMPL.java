package com.gaaji.Gaaji.Eccomerce.service.impl;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.CategoryDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.entity.Category;
import com.gaaji.Gaaji.Eccomerce.exeption.NotFoundException;
import com.gaaji.Gaaji.Eccomerce.mapper.EntityDtoMapper;
import com.gaaji.Gaaji.Eccomerce.repo.CategoryRepo;
import com.gaaji.Gaaji.Eccomerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    private final EntityDtoMapper entityDtoMapper;
    @Override
    public Responce createCategory(CategoryDTO categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Responce.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Responce updateCategory(Long categoryId, CategoryDTO categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category Not Found"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Responce.builder()
                .status(200)
                .message("category updated successfully")
                .build();
    }

    @Override
    public Responce getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoryDtoList = categories.stream()
                .map(entityDtoMapper::mapCategoryToDtoBasic)
                .collect(Collectors.toList());

        return  Responce.builder()
                .status(200)
                .categoryList(categoryDtoList)
                .build();
    }

    @Override
    public Responce getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category Not Found"));
        CategoryDTO categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category);
        return Responce.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }

    @Override
    public Responce deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category Not Found"));
        categoryRepo.delete(category);
        return Responce.builder()
                .status(200)
                .message("Category was deleted successfully")
                .build();
    }
}
