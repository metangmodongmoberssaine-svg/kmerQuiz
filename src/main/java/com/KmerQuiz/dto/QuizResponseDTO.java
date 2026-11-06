package com.KmerQuiz.dto;

import com.KmerQuiz.utils.PlayStatus;

import java.util.Date;
import java.util.List;

public record QuizResponseDTO(
        Long idQuiz,
        String titleQuiz,
        String descriptionQuiz,
        Date beginDateQuiz,
        Date endDateQuiz,
        double totalScoreQuiz,
        double minScoreQuiz,
        int nberOfQuestionsQuiz,
        int durationQuiz,
        PlayStatus quizStatus,
        boolean isValidQuiz,
        UserResponseDTO user,
        PartyResponseDTO party,
        List<QuizQuestionDTO> quizQuestions
) {
}
