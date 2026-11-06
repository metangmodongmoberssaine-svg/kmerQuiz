package com.KmerQuiz.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdUserGain implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idUser;
    private Long idGain;

    public IdUserGain() {
    }

    public IdUserGain(String idUser, Long idGain) {
        this.idUser = idUser;
        this.idGain = idGain;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getIdGain() {
        return idGain;
    }

    public void setIdGain(Long idGain) {
        this.idGain = idGain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdUserGain that = (IdUserGain) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idGain, that.idGain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGain);
    }
}
