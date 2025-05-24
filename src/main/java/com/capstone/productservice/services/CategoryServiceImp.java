package com.capstone.productservice.services;

import com.capstone.productservice.models.Category;
import com.capstone.productservice.repositories.CategoryRepository;

import java.util.List;

public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoryRepository.findByTitle(categoryName).orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }
}
