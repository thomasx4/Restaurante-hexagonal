package com.restaurante.hexagonal.application.service;

import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.domain.ports.input.ProductServicePort;
import com.restaurante.hexagonal.domain.ports.output.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductRepository productRepository;

    @Override
    @Transactional // Asegura que la operación de creación es atómica
    public Product createProduct(Product product) {
        // Validaciones de negocio
        validateProduct(product);
        
        // Si no se especifica disponibilidad, por defecto true
        if (product.getAvailable() == null) {
            product.setAvailable(true);
        }
        
        // Guardar el producto
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        // Verificar que el producto existe
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        
        // Validar los nuevos datos
        validateProduct(product);
        
        // Actualizar SOLO los campos permitidos
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategoryId(product.getCategoryId());
        
        // La disponibilidad se actualiza con el método específico
        // existingProduct.setAvailable(product.getAvailable()); 
        
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        // Verificar que existe
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAvailableProducts() {
        return productRepository.findByAvailable(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("El ID de categoría no puede ser nulo");
        }
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public void updateAvailability(Long id, boolean available) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        
        product.setAvailable(available);
        productRepository.save(product);
    }
    
    // Método privado para validar las reglas de negocio del producto
    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        
        if (product.getName().length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }
        
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }
    }

    @Override
    public Product patchProduct(Long id, Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchProduct'");
    }
}