package com.KmerQuiz.entities;

import com.KmerQuiz.utils.TypeRessource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRessources;

    @Basic(optional = false)
    private String UrlRessource;

    private String DescriptionRessource;

    @Basic(optional = false)
    private String ExtensionRessource;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private TypeRessource typeRessource;

    @ManyToOne
    @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "idGain", referencedColumnName = "idGain")
    private Gain gain;

    public Long getIdRessources() {
        return idRessources;
    }

    public void setIdRessources(Long idRessources) {
        this.idRessources = idRessources;
    }

    public String getUrlRessource() {
        return UrlRessource;
    }

    public void setUrlRessource(String urlRessource) {
        UrlRessource = urlRessource;
    }

    public String getDescriptionRessource() {
        return DescriptionRessource;
    }

    public void setDescriptionRessource(String descriptionRessource) {
        DescriptionRessource = descriptionRessource;
    }

    public String getExtensionRessource() {
        return ExtensionRessource;
    }

    public void setExtensionRessource(String extensionRessource) {
        ExtensionRessource = extensionRessource;
    }

    public TypeRessource getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Gain getGain() {
        return gain;
    }

    public void setGain(Gain gain) {
        this.gain = gain;
    }
}
