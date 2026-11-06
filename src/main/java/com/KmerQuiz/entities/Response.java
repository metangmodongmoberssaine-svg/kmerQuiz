package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long IdResponse;

    @Basic(optional = false)
    private String labelReponse;

    @Basic(optional = false)
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion")
    private Question question;

    public Long getIdResponse() {
        return IdResponse;
    }

    public void setIdResponse(Long idResponse) {
        IdResponse = idResponse;
    }

    public String getLabelReponse() {
        return labelReponse;
    }

    public void setLabelReponse(String labelReponse) {
        this.labelReponse = labelReponse;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
