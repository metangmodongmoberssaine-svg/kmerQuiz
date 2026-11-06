package com.KmerQuiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DifficultyRequestDTO(
        @NotBlank
        @Size(max = 50)
        String labelDifficulty
) {
}
