package com.KmerQuiz.dto;

import com.KmerQuiz.utils.TypeRessource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RessourceRequestDTO(
        @NotBlank
        String urlRessource,
        String descriptionRessource,
        @NotBlank
        String extensionRessource,
        @NotNull
        TypeRessource typeRessource,
        Long idQuestion,
        Long idGain
) {
}
