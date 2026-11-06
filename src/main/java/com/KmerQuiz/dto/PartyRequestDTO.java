package com.KmerQuiz.dto;

import com.KmerQuiz.utils.PlayStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PartyRequestDTO(
        @PositiveOrZero
        @Min(value = 1)
        int maxPlayers,
        @NotNull
        PlayStatus partyStatus
) {
}
