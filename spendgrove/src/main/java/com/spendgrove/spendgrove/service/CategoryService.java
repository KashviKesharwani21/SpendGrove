package com.spendgrove.spendgrove.service;

import com.spendgrove.spendgrove.entity.Category;
import com.spendgrove.spendgrove.entity.CategoryType;
import com.spendgrove.spendgrove.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name, CategoryType type) {
        Category category = new Category(name, type);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}