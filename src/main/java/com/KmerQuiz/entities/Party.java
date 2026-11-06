package com.KmerQuiz.entities;

import com.KmerQuiz.utils.PlayStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeParty;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    private int maxPlayers;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private PlayStatus partyStatus;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    public Long getCodeParty() {
        return codeParty;
    }

    public void setCodeParty(Long codeParty) {
        this.codeParty = codeParty;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public PlayStatus getPartyStatus() {
        return partyStatus;
    }

    public void setPartyStatus(PlayStatus partyStatus) {
        this.partyStatus = partyStatus;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }
}
