package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuizRequestDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.utils.PlayStatus;

import java.util.List;

public interface QuizServiceInt {
    QuizResponseDTO createQuiz(QuizRequestDTO requestDTO);
    QuizResponseDTO updateQuiz(Long idQuiz, QuizRequestDTO requestDTO);
    QuizResponseDTO getQuizById(Long idQuiz);
    List<QuizResponseDTO> getAllQuizzes();
    List<QuizResponseDTO> getQuizzesByUser(String idUser);
    List<QuizResponseDTO> getQuizzesByParty(Long codeParty);
    List<QuizResponseDTO> getQuizzesByStatus(PlayStatus status);
    QuizResponseDTO validateQuiz(Long idQuiz, boolean isValid);
    void deleteQuiz(Long idQuiz);
}
