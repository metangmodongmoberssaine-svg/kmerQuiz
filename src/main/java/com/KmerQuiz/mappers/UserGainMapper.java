package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.UserGame;
import com.KmerQuiz.entities.UserGain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GainMapper.class})
public interface UserGainMapper {

    @Mapping(source = "idUserGain.idUser", target = "idUser")
    @Mapping(source = "idUserGain.idGain", target = "idGain")
    @Mapping(source = "gettingDate", target = "gettingDate")
    @Mapping(source = "gain", target = "gain")
    UserGame toDTO(UserGain userGain);

    @Mapping(source = "idUser", target = "idUserGain.idUser")
    @Mapping(source = "idGain", target = "idUserGain.idGain")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "gain", ignore = true)
    UserGain toEntity(UserGame dto);

    List<UserGame> toDTOList(List<UserGain> userGains);
}
