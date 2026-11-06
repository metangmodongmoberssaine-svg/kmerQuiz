package com.KmerQuiz.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record QuestionRequestDTO(
        @NotBlank
        String questionText1,
        @Nullable
        String questionText2,
        @Nullable
        String questionText3,
        @Positive
        int durationQuestion,
        @NotNull
        Long idDifficulty,
        @NotEmpty
        List<Long> idCategories
) {
}
