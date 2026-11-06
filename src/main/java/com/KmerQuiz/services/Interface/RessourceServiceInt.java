package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.RessourceRequestDTO;
import com.KmerQuiz.dto.RessourceResponseDTO;
import com.KmerQuiz.utils.TypeRessource;

import java.util.List;

public interface RessourceServiceInt {
    RessourceResponseDTO save(RessourceRequestDTO ressourcerequestDTO);
    RessourceResponseDTO update(Long idRessources, RessourceRequestDTO requestDTO);
    RessourceResponseDTO findById(Long idRessources);
    List<RessourceResponseDTO> getAllRessources();
    List<RessourceResponseDTO> getRessourcesByType(TypeRessource typeRessource);
    List<RessourceResponseDTO> getRessourcesByQuestion(Long idQuestion);
    List<RessourceResponseDTO> getRessourcesByGain(Long idGain);
    void deleteRessource(Long idRessources);
}
