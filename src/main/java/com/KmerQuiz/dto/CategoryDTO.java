package com.KmerQuiz.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO(
        @Nullable
        Long idCategory,
        @NotBlank
        @Size(min = 5, max = 100)
        String labelCategory,
        @Nullable
        String description

) {
}
