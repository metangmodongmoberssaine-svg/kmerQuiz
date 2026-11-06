package com.KmerQuiz.dto;

import jakarta.validation.constraints.NotNull;

public record QuizQuestionDTO(
        @NotNull
        Long idQuiz,
        @NotNull
        Long idQuestion,
        boolean Status
) {
}
