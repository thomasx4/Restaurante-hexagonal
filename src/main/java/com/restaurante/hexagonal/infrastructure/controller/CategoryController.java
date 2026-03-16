package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.restaurante.hexagonal.application.dto.CategoryRequestDTO;
import com.restaurante.hexagonal.application.dto.CategoryResponseDTO;
import com.restaurante.hexagonal.domain.model.Category;
import com.restaurante.hexagonal.domain.ports.input.CategoryServicePort;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServicePort categoryService;

    public CategoryController(CategoryServicePort categoryService) {
        this.categoryService = categoryService;
    }

    // POST - Crear categoría
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        Category createdCategory = categoryService.createCategory(category);
        
        CategoryResponseDTO response = mapToResponse(createdCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET - Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponseDTO> responses = categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // GET - Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(mapToResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Actualizar categoría completa
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {
        
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(mapToResponse(updatedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH - Actualizar parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> patchCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {
        
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        try {
            Category patchedCategory = categoryService.patchCategory(id, category);
            return ResponseEntity.ok(mapToResponse(patchedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método privado para mapear Domain a ResponseDTO
    private CategoryResponseDTO mapToResponse(Category category) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
}