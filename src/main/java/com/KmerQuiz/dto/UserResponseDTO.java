package com.KmerQuiz.dto;

import com.KmerQuiz.utils.RoleUser;

import java.util.Date;

public record UserResponseDTO(
     String IdUser,
     String pseudoUser,
     String emailUser,
     String phoneUser,
     String avatarUser,
     RoleUser roleUser,
     Date inscriptionDataUser
) {
}
