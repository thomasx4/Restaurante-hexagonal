package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;
import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }
        
        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setPrice(domain.getPrice() != null ? domain.getPrice().floatValue() : null);
        entity.setAvailable(domain.getAvailable());
        
        // Mapear la categoría si existe
        if (domain.getCategoryId() != null) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(domain.getCategoryId());
            entity.setCategory(categoryEntity);
        }
        
        return entity;
    }

    /**
     * Convierte de ENTITY a DOMAIN (para trabajar en la lógica de negocio)
     */
    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Product domain = new Product();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        domain.setPrice(entity.getPrice() != null ? entity.getPrice().doubleValue() : null);
        domain.setAvailable(entity.getAvailable());
        
        // Mapear la categoría si existe
        if (entity.getCategory() != null) {
            domain.setCategoryId(entity.getCategory().getId());
        }
        
        return domain;
    }
    
    // Convierte lista de ENTITY a lista de DOMAIN
    public List<Product> toDomainList(List<ProductEntity> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    // Convierte lista de DOMAIN a lista de ENTITY
    public List<ProductEntity> toEntityList(List<Product> domains) {
        if (domains == null) {
            return null;
        }
        
        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
