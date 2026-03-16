package com.restaurante.hexagonal.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.domain.ports.input.CategoryServicePort;
import com.restaurante.hexagonal.domain.ports.output.CategoryRepositoryPort;

@Service
public class CategoryService implements CategoryServicePort {

    private final CategoryRepositoryPort categoryRepository;

    public CategoryService(CategoryRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category patchCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        if (category.getName() != null) {
            existingCategory.setName(category.getName());
        }
        if (category.getDescription() != null) {
            existingCategory.setDescription(category.getDescription());
        }
        
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
