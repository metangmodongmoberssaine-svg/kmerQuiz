package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserParty {
    @EmbeddedId
    private IdUserParty idUserParty;

    private double scorePlayer;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "codeParty", referencedColumnName = "codeParty", insertable = false, updatable = false)
    private Party party;

    public IdUserParty getIdUserParty() {
        return idUserParty;
    }

    public void setIdUserParty(IdUserParty idUserParty) {
        this.idUserParty = idUserParty;
    }

    public double getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(double scorePlayer) {
        this.scorePlayer = scorePlayer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
