package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.CategoryDTO;
import com.KmerQuiz.entities.Category;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.CategoryMapper;
import com.KmerQuiz.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements com.KmerQuiz.services.Interface.CategoryServiceInt {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByLabelCategoryIgnoreCase(categoryDTO.labelCategory())) {
            throw new DuplicatedRessourcException("Category", "labelCategory", categoryDTO.labelCategory());
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RessourceNotFoundException("Category", "idCategory", idCategory));

        if (!existingCategory.getLabelCategory().equalsIgnoreCase(categoryDTO.labelCategory()) &&
                categoryRepository.existsByLabelCategoryIgnoreCase(categoryDTO.labelCategory())) {
            throw new DuplicatedRessourcException("Category", "labelCategory", categoryDTO.labelCategory());
        }

        categoryMapper.updateEntityFromDTO(categoryDTO, existingCategory);
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(updatedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long idCategory) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RessourceNotFoundException("Category", "idCategory", idCategory));
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.toDTOList(categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Long idCategory) {
        if (!categoryRepository.existsById(idCategory)) {
            throw new RessourceNotFoundException("Category", "idCategory", idCategory);
        }
        categoryRepository.deleteById(idCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> searchCategories(String keyword) {
        return categoryMapper.toDTOList(categoryRepository.findByLabelCategoryContainingIgnoreCase(keyword));
    }
}
