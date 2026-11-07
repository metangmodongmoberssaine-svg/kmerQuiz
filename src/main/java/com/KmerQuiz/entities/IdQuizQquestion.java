package com.KmerQuiz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdQuizQquestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idQuiz;

    // Même colonne (id_question) que le @JoinColumn(name = "idQuestion") de QuizQuestion
    @Column(name = "idQuestion")
    private Long idQquestion;
}
