package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuizQuestionDTO;
import com.KmerQuiz.dto.QuizRequestDTO;

import java.util.List;

public interface QuizQuestionServiceInt {
    QuizQuestionDTO save(QuizRequestDTO quizRequestDTO);
    QuizRequestDTO update(QuizRequestDTO quizRequestDTO );
    QuizQuestionDTO addQuestionToQuiz(Long idQuiz, Long idQuestion, boolean status);
    void removeQuestionFromQuiz(Long idQuiz, Long idQuestion);
    List<QuizQuestionDTO> getQuestionsByQuiz(Long idQuiz);
    List<QuizQuestionDTO> getQuizzesByQuestion(Long idQuestion);
    QuizQuestionDTO updateQuizQuestionStatus(Long idQuiz, Long idQuestion, boolean status);
}
