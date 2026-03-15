package com.restaurante.hexagonal.domain.ports.input;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Category;

public interface CategoryServicePort {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    Category patchCategory(Long id, Category category);
    void deleteCategory(Long id);
}