package com.KmerQuiz.dto;

import com.KmerQuiz.utils.TypeGain;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GainDTO(
        @Nullable
        Long idGain,
        @PositiveOrZero
        double scoreMin,
        @NotNull
        TypeGain typeGain,
        @NotNull
        String labelGain
) {
}
