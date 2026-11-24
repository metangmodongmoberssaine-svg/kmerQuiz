package com.KmerQuiz.dto;

import com.KmerQuiz.utils.RoleUser;
import lombok.Getter;

import java.util.Date;

public record UserResponseDTO(
     String idUser,
     String pseudoUser,
     String emailUser,
     String phoneUser,
     String avatarUser,
     RoleUser roleUser,
     Date inscriptionDataUser
) {
}
