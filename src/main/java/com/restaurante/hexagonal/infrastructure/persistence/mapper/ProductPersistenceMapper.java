package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component  // Importante para que Spring lo detecte
public class ProductPersistenceMapper {

    public ProductEntity toEntity(Product domain) {
        if (domain == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setPrice(domain.getPrice());
        entity.setAvailable(domain.getAvailable());
        entity.setCategoryId(domain.getCategoryId());

        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        Product domain = new Product();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        domain.setPrice(entity.getPrice());
        domain.setAvailable(entity.getAvailable());
        domain.setCategoryId(entity.getCategoryId());

        return domain;
    }
}