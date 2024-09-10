package com.gaaji.Gaaji.Eccomerce.service;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.CategoryDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;

public interface CategoryService {
    Responce createCategory(CategoryDTO categoryRequest);
    Responce updateCategory(Long categoryId, CategoryDTO categoryRequest);
    Responce getAllCategories();
    Responce getCategoryById(Long categoryId);
    Responce deleteCategory(Long categoryId);
}
