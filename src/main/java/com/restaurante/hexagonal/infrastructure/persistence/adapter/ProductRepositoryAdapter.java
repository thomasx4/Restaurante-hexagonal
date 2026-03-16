package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.domain.ports.output.ProductRepository;
import com.restaurante.hexagonal.infrastructure.persistence.entity.ProductEntity;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.ProductPersistenceMapper;
import com.restaurante.hexagonal.infrastructure.persistence.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component  // ← ¡Esta anotación es CRUCIAL! Hace que Spring detecte esta clase
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {  // ← Implementa la interfaz

    private final ProductJpaRepository ProductJpaRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity savedEntity = ProductJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return ProductJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return ProductJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByAvailable(boolean available) {
        return ProductJpaRepository.findByAvailable(available).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return ProductJpaRepository.findByCategoryId(categoryId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        ProductJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return ProductJpaRepository.existsById(id);
    }
}