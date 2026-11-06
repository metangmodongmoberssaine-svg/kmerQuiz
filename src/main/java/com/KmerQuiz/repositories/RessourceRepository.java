package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Ressource;
import com.KmerQuiz.utils.TypeRessource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    List<Ressource> findByTypeRessource(TypeRessource typeRessource);
    List<Ressource> findByQuestion_IdQuestion(Long idQuestion);
    List<Ressource> findByGain_IdGain(Long idGain);
}
