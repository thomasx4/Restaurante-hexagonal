package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;

@Component
public class CategoryMapper {

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
        category.setDescription(entity.getDescription());
        return category;
    }

    public CategoryEntity toEntity(Category category) {
        if (category == null) return null;
        
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        return entity;
    }
}
