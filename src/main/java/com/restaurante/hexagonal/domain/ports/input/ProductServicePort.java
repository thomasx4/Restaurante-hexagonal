package com.restaurante.hexagonal.domain.ports.input;

import com.restaurante.hexagonal.domain.model.Product;
import java.util.List;
import java.util.Optional;

// Este es un PUERTO DE ENTRADA - Define QUÉ puede hacer el sistema con productos
public interface ProductServicePort {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAvailableProducts();
    List<Product> getProductsByCategory(Long categoryId);
    void updateAvailability(Long id, boolean available);
}