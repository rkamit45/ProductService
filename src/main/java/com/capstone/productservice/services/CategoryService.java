package com.capstone.productservice.services;

import com.capstone.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(Long categoryId);
    Category getCategory(String categoryName);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long categoryId);
}
