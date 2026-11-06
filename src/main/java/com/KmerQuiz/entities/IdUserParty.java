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
public class IdUserParty implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idUser;
    private Long codeParty;

    public IdUserParty() {
    }

    public IdUserParty(String idUser, Long codeParty) {
        this.idUser = idUser;
        this.codeParty = codeParty;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getCodeParty() {
        return codeParty;
    }

    public void setCodeParty(Long codeParty) {
        this.codeParty = codeParty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdUserParty that = (IdUserParty) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(codeParty, that.codeParty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, codeParty);
    }
}
