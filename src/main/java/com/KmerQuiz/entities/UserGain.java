package com.KmerQuiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserGain {
    @EmbeddedId
    private IdUserGain idUserGain;

    @Temporal(TemporalType.TIMESTAMP)
    private Date gettingDate;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGain", referencedColumnName = "idGain", insertable = false, updatable = false)
    private Gain gain;

    public IdUserGain getIdUserGain() {
        return idUserGain;
    }

    public void setIdUserGain(IdUserGain idUserGain) {
        this.idUserGain = idUserGain;
    }

    public Date getGettingDate() {
        return gettingDate;
    }

    public void setGettingDate(Date gettingDate) {
        this.gettingDate = gettingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gain getGain() {
        return gain;
    }

    public void setGain(Gain gain) {
        this.gain = gain;
    }
}
