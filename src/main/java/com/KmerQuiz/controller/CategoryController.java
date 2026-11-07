package com.KmerQuiz.controller;

import com.KmerQuiz.dto.CategoryDTO;
import com.KmerQuiz.services.Interface.CategoryServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceInt categoryService;

    // Créer une catégorie
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
    }

    // Afficher toutes les catégories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Rechercher par mot-clé : /search?keyword=hist
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(categoryService.searchCategories(keyword));
    }

    // Afficher une catégorie
    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long idCategory) {
        return ResponseEntity.ok(categoryService.getCategoryById(idCategory));
    }

    // Modifier une catégorie
    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable Long idCategory,
            @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(idCategory, categoryDTO));
    }

    // Supprimer une catégorie
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long idCategory) {
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }
}
