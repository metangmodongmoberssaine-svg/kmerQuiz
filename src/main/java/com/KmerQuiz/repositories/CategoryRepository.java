package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByLabelCategoryIgnoreCase(String labelCategory);
    boolean existsByLabelCategoryIgnoreCase(String labelCategory);
    List<Category> findByLabelCategoryContainingIgnoreCase(String keyword);
}
