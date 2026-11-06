package com.KmerQuiz.dto;

import com.KmerQuiz.utils.TypeRessource;

public record RessourceResponseDTO(
        Long idRessources,
        String urlRessource,
        String descriptionRessource,
        String extensionRessource,
        TypeRessource typeRessource,
        Long idQuestion,
        Long idGain
) {
}
