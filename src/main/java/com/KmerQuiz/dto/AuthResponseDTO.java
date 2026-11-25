package com.KmerQuiz.dto;

public record AuthResponseDTO(
        String token,
        String tokenType,
        UserResponseDTO user
) {
}
