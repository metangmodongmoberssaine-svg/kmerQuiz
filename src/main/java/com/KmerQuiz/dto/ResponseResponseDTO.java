package com.KmerQuiz.dto;

public record ResponseResponseDTO(
        Long idResponse,
        String labelReponse,
        Boolean isCorrect,
        Long idQuestion
) {
}
