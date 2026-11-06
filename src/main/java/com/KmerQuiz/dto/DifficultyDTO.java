package com.KmerQuiz.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DifficultyDTO(
        @Nullable
        Long idDifficulty,
        @NotBlank
        @Size(max = 50)
        String labelDifficulty

) {
}
