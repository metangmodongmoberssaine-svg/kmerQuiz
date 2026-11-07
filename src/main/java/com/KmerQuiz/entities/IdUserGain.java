package com.KmerQuiz.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdUserGain implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idUser;
    private Long idGain;
}
