package com.restaurante.hexagonal.infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
     // Spring Data JPA interpreta estos métodos automáticamente
    List<ProductEntity> findByAvailable(boolean available);
    
    List<ProductEntity> findByCategoryId(Long categoryId);
}
