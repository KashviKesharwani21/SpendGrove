package com.spendgrove.spendgrove.controller;

import com.spendgrove.spendgrove.entity.Category;
import com.spendgrove.spendgrove.entity.CategoryType;
import com.spendgrove.spendgrove.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request.name(), request.type());
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    public record CreateCategoryRequest(String name, CategoryType type) {}
}