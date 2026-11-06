package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuizRequestDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.utils.PlayStatus;
import com.KmerQuiz.utils.TypeGain;
import lombok.Data;

import java.util.Date;
import java.util.List;

public interface QuizServiceInt {
    QuizResponseDTO save(QuizRequestDTO quizRequestDTO);
    QuizResponseDTO createQuiz(QuizRequestDTO requestDTO);
    QuizResponseDTO updateQuiz(Long idQuiz, QuizRequestDTO requestDTO);
    QuizResponseDTO getQuizById(Long idQuiz);
    List<QuizResponseDTO> getAllQuizes();
    List<QuizResponseDTO> getQuizzesByUser(String idUser);
    List<QuizResponseDTO> findAllByEndDate(Date endDate);
    List<QuizResponseDTO> findAllByTypeGain(TypeGain typeGain);
    void deleteQuiz(Long idQuiz);
}
