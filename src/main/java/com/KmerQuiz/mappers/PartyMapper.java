package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.PartyDTO;
import com.KmerQuiz.dto.PartyRequestDTO;
import com.KmerQuiz.dto.PartyResponseDTO;
import com.KmerQuiz.entities.Party;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {

    PartyDTO toDTO(Party party);

    @Mapping(source = "createdDate", target = "creationDate")
    PartyResponseDTO toResponseDTO(Party party);

    @Mapping(target = "quiz", ignore = true)
    Party toEntity(PartyDTO partyDTO);

    @Mapping(target = "codeParty", ignore = true)
    @Mapping(target = "createdDate", expression = "java(new java.util.Date())")
    @Mapping(target = "quiz", ignore = true)
    Party toEntity(PartyRequestDTO requestDTO);

    List<PartyDTO> toDTOList(List<Party> parties);

    List<PartyResponseDTO> toResponseDTOList(List<Party> parties);

    @Mapping(target = "codeParty", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    void updateEntityFromDTO(PartyRequestDTO dto, @MappingTarget Party entity);
}
