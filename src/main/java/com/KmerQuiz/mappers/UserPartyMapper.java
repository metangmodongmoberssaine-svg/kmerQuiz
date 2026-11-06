package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.UserPartyDTO;
import com.KmerQuiz.entities.UserParty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPartyMapper {

    @Mapping(source = "idUserParty.idUser", target = "IdUser")
    @Mapping(source = "idUserParty.codeParty", target = "codeParty")
    @Mapping(source = "scorePlayer", target = "score")
    UserPartyDTO toDTO(UserParty userParty);

    @Mapping(source = "IdUser", target = "idUserParty.idUser")
    @Mapping(source = "codeParty", target = "idUserParty.codeParty")
    @Mapping(source = "score", target = "scorePlayer")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "party", ignore = true)
    UserParty toEntity(UserPartyDTO dto);

    List<UserPartyDTO> toDTOList(List<UserParty> userParties);
}
