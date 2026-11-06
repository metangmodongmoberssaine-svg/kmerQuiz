package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.CategoryDTO;
import com.KmerQuiz.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);

    @Mapping(target = "questions", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDTOList(List<Category> categories);

    List<Category> toEntityList(List<CategoryDTO> dtos);

    @Mapping(target = "idCategory", ignore = true)
    @Mapping(target = "questions", ignore = true)
    void updateEntityFromDTO(CategoryDTO dto, @MappingTarget Category entity);
}
