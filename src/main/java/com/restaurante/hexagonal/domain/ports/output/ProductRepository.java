package com.restaurante.hexagonal.domain.ports.output;

import com.restaurante.hexagonal.domain.model.Product;
import java.util.List;
import java.util.Optional;

// Define lo que el dominio necesita del exterior (BD)
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByAvailable(boolean available);
    List<Product> findByCategoryId(Long categoryId);
    void deleteById(Long id);
    boolean existsById(Long id);
}
