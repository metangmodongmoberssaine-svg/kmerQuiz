package com.KmerQuiz.dto;

import java.util.List;

public record QuestionResponseDTO(
        Long idQuestion,
        String questionText1,
        String questionText2,
        String questionText3,
        int durationQuestion,
        DifficultyDTO difficulty,
        List<CategoryDTO> categories,
        List<ResponseResponseDTO> responses,
        List<RessourceResponseDTO> ressources
) {
}
