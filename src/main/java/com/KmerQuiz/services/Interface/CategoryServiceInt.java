package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceInt {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long idCategory, CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long idCategory);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(Long idCategory);
    List<CategoryDTO> searchCategories(String keyword);
}
