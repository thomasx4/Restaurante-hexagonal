package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.domain.ports.output.CategoryRepositoryPort;
import com.restaurante.hexagonal.infrastructure.persistence.entity.CategoryEntity;
import com.restaurante.hexagonal.infrastructure.persistence.jpa.CategoryJpaRepository;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.CategoryMapper;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository jpaRepository;
    private final CategoryMapper mapper;

    public CategoryRepositoryAdapter(CategoryJpaRepository jpaRepository, CategoryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = mapper.toEntity(category);
        CategoryEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}