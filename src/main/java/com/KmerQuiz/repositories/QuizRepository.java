package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Quiz;
import com.KmerQuiz.utils.PlayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByUser_IdUser(String idUser);
    List<Quiz> findByParty_CodeParty(Long codeParty);
    List<Quiz> findByQuizStatus(PlayStatus quizStatus);
    List<Quiz> findByIsValidQuizTrue();
    List<Quiz> findByTitreQuizContainingIgnoreCase(String keyword);
}
