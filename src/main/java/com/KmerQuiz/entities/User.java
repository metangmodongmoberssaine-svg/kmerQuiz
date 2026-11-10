package com.KmerQuiz.entities;

import com.KmerQuiz.utils.RoleUser;
import com.KmerQuiz.utils.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity(name = "User")
public class User {
    @Id
    private String idUser;

    @Basic(optional = false)
    private String pseudoUser;

    private String emailUser;

    @Basic(optional = false)
    private String phoneUser;

    private String avatarUser;

    @Basic(optional = false)
    private String passwordUser;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inscriptionDataUser;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Quiz> quizList;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPseudoUser() {
        return pseudoUser;
    }

    public void setPseudoUser(String pseudoUser) {
        this.pseudoUser = pseudoUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        this.avatarUser = avatarUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Date getInscriptionDataUser() {
        return inscriptionDataUser;
    }

    public void setInscriptionDataUser(Date inscriptionDataUser) {
        this.inscriptionDataUser = inscriptionDataUser;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

        public UserStatus getStatusUser() {
        return statusUser;
    }
}
