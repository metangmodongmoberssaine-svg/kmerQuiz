package com.KmerQuiz.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.util.Date;

public record QuizRequestDTO(
        @NotBlank
        @Size(min = 5, max = 150)
        String titleQuiz,
        @Nullable
        String descriptionQuiz,
        @Nullable
        @FutureOrPresent
        Date beginDateQuiz,
        @Nullable
        Date endDateQuiz,
        @Positive
        double totalScoreQuiz,
        @Positive
        double minScoreQuiz,
        @Positive
        int durationQuiz,
        @NotBlank
        String idUser,
        @NotNull
        String codeParty
) {
}
