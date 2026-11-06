package com.KmerQuiz.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdQuizQquestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idQuiz;
    private Long idQquestion;

    public IdQuizQquestion() {
    }

    public IdQuizQquestion(Long idQuiz, Long idQquestion) {
        this.idQuiz = idQuiz;
        this.idQquestion = idQquestion;
    }

    public Long getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Long idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Long getIdQquestion() {
        return idQquestion;
    }

    public void setIdQquestion(Long idQquestion) {
        this.idQquestion = idQquestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdQuizQquestion that = (IdQuizQquestion) o;
        return Objects.equals(idQuiz, that.idQuiz) && Objects.equals(idQquestion, that.idQquestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuiz, idQquestion);
    }
}
