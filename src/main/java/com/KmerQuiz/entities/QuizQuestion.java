package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {
    @EmbeddedId
    private IdQuizQquestion idQuizQquestion;

    @Basic(optional = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion", insertable = false, updatable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "idQuiz", referencedColumnName = "idQuiz", insertable = false, updatable = false)
    private Quiz quiz;

    public IdQuizQquestion getIdQuizQquestion() {
        return idQuizQquestion;
    }

    public void setIdQuizQquestion(IdQuizQquestion idQuizQquestion) {
        this.idQuizQquestion = idQuizQquestion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
