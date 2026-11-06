package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByQuestion_IdQuestion(Long idQuestion);
    List<Response> findByQuestion_IdQuestionAndIsCorrectTrue(Long idQuestion);
    void deleteByQuestion_IdQuestion(Long idQuestion);
}
