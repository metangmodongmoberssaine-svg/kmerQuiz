package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDifficulty;

    @Basic(optional = false)
    private String labelDifficulty;

    @OneToMany(mappedBy = "difficulty", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getLabelDifficulty() {
        return labelDifficulty;
    }

    public void setLabelDifficulty(String labelDifficulty) {
        this.labelDifficulty = labelDifficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
