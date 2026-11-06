package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.IdQuizQquestion;
import com.KmerQuiz.entities.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, IdQuizQquestion> {
    List<QuizQuestion> findByIdQuizQquestion_IdQuiz(Long idQuiz);
    List<QuizQuestion> findByIdQuizQquestion_IdQquestion(Long idQuestion);
    boolean existsByIdQuizQquestion_IdQuizAndIdQuizQquestion_IdQquestion(Long idQuiz, Long idQuestion);
    void deleteByIdQuizQquestion_IdQuizAndIdQuizQquestion_IdQquestion(Long idQuiz, Long idQuestion);
}
