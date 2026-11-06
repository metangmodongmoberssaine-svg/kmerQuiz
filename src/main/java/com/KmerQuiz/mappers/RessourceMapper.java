package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.RessourceRequestDTO;
import com.KmerQuiz.dto.RessourceResponseDTO;
import com.KmerQuiz.entities.Ressource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RessourceMapper {

    @Mapping(source = "urlRessource", target = "urlRessource")
    @Mapping(source = "descriptionRessource", target = "descriptionRessource")
    @Mapping(source = "extensionRessource", target = "extensionRessource")
    @Mapping(source = "question.idQuestion", target = "idQuestion")
    @Mapping(source = "gain.idGain", target = "idGain")
    RessourceResponseDTO toResponseDTO(Ressource ressource);

    @Mapping(source = "urlRessource", target = "urlRessource")
    @Mapping(source = "descriptionRessource", target = "descriptionRessource")
    @Mapping(source = "extensionRessource", target = "extensionRessource")
    @Mapping(target = "idRessources", ignore = true)
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "gain", ignore = true)
    Ressource toEntity(RessourceRequestDTO requestDTO);

    List<RessourceResponseDTO> toResponseDTOList(List<Ressource> ressources);

    @Mapping(source = "urlRessource", target = "urlRessource")
    @Mapping(source = "descriptionRessource", target = "descriptionRessource")
    @Mapping(source = "extensionRessource", target = "extensionRessource")
    @Mapping(target = "idRessources", ignore = true)
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "gain", ignore = true)
    void updateEntityFromDTO(RessourceRequestDTO dto, @MappingTarget Ressource entity);
}
