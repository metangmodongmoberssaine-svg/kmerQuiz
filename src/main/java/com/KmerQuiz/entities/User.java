package com.KmerQuiz.entities;

import com.KmerQuiz.utils.RoleUser;
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
    @Column(length = 50, unique = true)
    private String pseudoUser;

    @Column(length = 50, unique = true)
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

    @Basic(optional = false)
    private String statusUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Quiz> quizList;


}
