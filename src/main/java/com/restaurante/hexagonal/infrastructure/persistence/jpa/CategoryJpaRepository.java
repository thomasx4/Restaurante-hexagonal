package com.restaurante.hexagonal.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}