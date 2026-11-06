package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.dto.QuestionResponseDTO;

import java.util.List;

public interface QuestionServiceInt {
    QuestionResponseDTO createQuestion(QuestionRequestDTO requestDTO);
    QuestionResponseDTO updateQuestion(Long idQuestion, QuestionRequestDTO requestDTO);
    QuestionResponseDTO getQuestionById(Long idQuestion);
    List<QuestionResponseDTO> getAllQuestions();
    List<QuestionResponseDTO> getQuestionsByDifficulty(Long idDifficulty);
    List<QuestionResponseDTO> getQuestionsByCategory(Long idCategory);
    void deleteQuestion(Long idQuestion);
}
