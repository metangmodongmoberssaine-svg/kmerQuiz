package com.KmerQuiz.entities;

import com.KmerQuiz.utils.TypeGain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGain;

    @Enumerated(EnumType.STRING)
    private TypeGain typeGain;

    private double scoreGain;

    @OneToMany(mappedBy = "gain", cascade = CascadeType.ALL)
    private List<Ressource> ressources;

    public Long getIdGain() {
        return idGain;
    }

    public void setIdGain(Long idGain) {
        this.idGain = idGain;
    }

    public TypeGain getTypeGain() {
        return typeGain;
    }

    public void setTypeGain(TypeGain typeGain) {
        this.typeGain = typeGain;
    }

    public double getScoreGain() {
        return scoreGain;
    }

    public void setScoreGain(double scoreGain) {
        this.scoreGain = scoreGain;
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
}
