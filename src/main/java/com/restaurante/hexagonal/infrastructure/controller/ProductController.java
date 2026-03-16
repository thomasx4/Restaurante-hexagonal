package com.restaurante.hexagonal.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.hexagonal.application.dto.ProductRequestDTO;
import com.restaurante.hexagonal.application.dto.ProductResponseDTO;
import com.restaurante.hexagonal.domain.model.Product;
import com.restaurante.hexagonal.domain.ports.input.ProductServicePort;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServicePort productService;

    public ProductController(ProductServicePort productService) {
        this.productService = productService;
    }

    // POST - Crear producto
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());
        product.setAvailable(request.isAvailable());
        
        Product createdProduct = productService.createProduct(product);
        
        ProductResponseDTO response = mapToResponse(createdProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET - Obtener todos los productos
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // GET - Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(mapToResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Actualizar producto completo
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO request) {
        
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());
        product.setAvailable(request.isAvailable());
        
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(mapToResponse(updatedProduct));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH - Actualizar parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> patchProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO request) {
        
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());
        product.setAvailable(request.isAvailable());
        
        try {
            Product patchedProduct = productService.patchProduct(id, product);
            return ResponseEntity.ok(mapToResponse(patchedProduct));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private ProductResponseDTO mapToResponse(Product product) {
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getCategoryId());
        response.setAvailable(product.getAvailable());
        return response;
    }
}
