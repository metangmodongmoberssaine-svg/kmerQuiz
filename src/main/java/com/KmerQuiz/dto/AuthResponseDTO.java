package com.KmerQuiz.dto;

public record AuthResponseDTO<userResponseDTO>(
        String token,
        String tokenType,
        userResponseDTO user
) {
}
