package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestion;

    @Basic(optional = false)
    private String questionText1;
    private String questionText2;
    private String questionText3;

    @Basic(optional = false)
    private int durationQuestion;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions;

    @ManyToOne
    @JoinColumn(name = "idDifficulty", referencedColumnName = "idDifficulty")
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(
            name = "question_category",
            joinColumns = @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion"),
            inverseJoinColumns = @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Ressource> ressources;

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionText1() {
        return questionText1;
    }

    public void setQuestionText1(String questionText1) {
        this.questionText1 = questionText1;
    }

    public String getQuestionText2() {
        return questionText2;
    }

    public void setQuestionText2(String questionText2) {
        this.questionText2 = questionText2;
    }

    public String getQuestionText3() {
        return questionText3;
    }

    public void setQuestionText3(String questionText3) {
        this.questionText3 = questionText3;
    }

    public int getDurationQuestion() {
        return durationQuestion;
    }

    public void setDurationQuestion(int durationQuestion) {
        this.durationQuestion = durationQuestion;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
}
