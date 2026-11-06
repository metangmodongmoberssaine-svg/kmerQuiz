package com.KmerQuiz.dto;

import com.KmerQuiz.utils.RoleUser;
import jakarta.persistence.Basic;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record UserRequestDTO (
    @NotBlank
    @Size(min = 8, max = 10)
    String idUser,
    String pseudoUser,
    @Email
    String emailUser,
    @Size(min = 9, max = 12)
    String phoneUser,
    String avatarUser,
    @Size(min = 8)
    String passwordUser


){

}
