package com.KmerQuiz.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String pseudoUser,
        @NotBlank String passwordUser
) {
}
