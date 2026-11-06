package com.KmerQuiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseRequestDTO(
        @NotBlank
        String labelReponse,
        @NotNull
        Boolean isCorrect,
        @NotNull
        Long idQuestion
) {
}
