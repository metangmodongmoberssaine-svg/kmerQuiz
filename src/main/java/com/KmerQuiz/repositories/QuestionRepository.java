package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficulty_IdDifficulty(Long idDifficulty);
    List<Question> findByCategories_IdCategory(Long idCategory);
    List<Question> findByQuestionText1ContainingIgnoreCase(String keyword);
    List<Question> findByDurationQuestionLessThanEqual(int maxDuration);
}
