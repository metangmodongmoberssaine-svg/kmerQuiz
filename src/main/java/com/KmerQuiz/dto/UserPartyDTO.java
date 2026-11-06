package com.KmerQuiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record UserPartyDTO(
        @NotBlank
        String IdUser,
        @NotBlank
        String codeParty,
        @PositiveOrZero
        Double score
) {
}
