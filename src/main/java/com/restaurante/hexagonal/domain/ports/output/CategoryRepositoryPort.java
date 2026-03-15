package com.restaurante.hexagonal.domain.ports.output;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Category;

public interface CategoryRepositoryPort {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}