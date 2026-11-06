package com.KmerQuiz.entities;

import com.KmerQuiz.utils.PlayStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuiz;

    @Basic(optional = false)
    private String titreQuiz;

    private String descriptionQuiz;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDateQuiz;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateQuiz;

    @Basic(optional = false)
    private double totalScoreQuiz;

    private double minScoreQuiz;

    @Basic(optional = false)
    private int nberOfQuestionsQuiz;

    @Basic(optional = false)
    private int durationQuiz;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private PlayStatus quizStatus;

    private boolean isValidQuiz;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions;

    @ManyToOne
    @JoinColumn(name = "codeParty", referencedColumnName = "codeParty")
    private Party party;

    public Long getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Long idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTitreQuiz() {
        return titreQuiz;
    }

    public void setTitreQuiz(String titreQuiz) {
        this.titreQuiz = titreQuiz;
    }

    public String getDescriptionQuiz() {
        return descriptionQuiz;
    }

    public void setDescriptionQuiz(String descriptionQuiz) {
        this.descriptionQuiz = descriptionQuiz;
    }

    public Date getBeginDateQuiz() {
        return beginDateQuiz;
    }

    public void setBeginDateQuiz(Date beginDateQuiz) {
        this.beginDateQuiz = beginDateQuiz;
    }

    public Date getEndDateQuiz() {
        return endDateQuiz;
    }

    public void setEndDateQuiz(Date endDateQuiz) {
        this.endDateQuiz = endDateQuiz;
    }

    public double getTotalScoreQuiz() {
        return totalScoreQuiz;
    }

    public void setTotalScoreQuiz(double totalScoreQuiz) {
        this.totalScoreQuiz = totalScoreQuiz;
    }

    public double getMinScoreQuiz() {
        return minScoreQuiz;
    }

    public void setMinScoreQuiz(double minScoreQuiz) {
        this.minScoreQuiz = minScoreQuiz;
    }

    public int getNberOfQuestionsQuiz() {
        return nberOfQuestionsQuiz;
    }

    public void setNberOfQuestionsQuiz(int nberOfQuestionsQuiz) {
        this.nberOfQuestionsQuiz = nberOfQuestionsQuiz;
    }

    public int getDurationQuiz() {
        return durationQuiz;
    }

    public void setDurationQuiz(int durationQuiz) {
        this.durationQuiz = durationQuiz;
    }

    public PlayStatus getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(PlayStatus quizStatus) {
        this.quizStatus = quizStatus;
    }

    public boolean isValidQuiz() {
        return isValidQuiz;
    }

    public void setValidQuiz(boolean validQuiz) {
        isValidQuiz = validQuiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
