package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.entities.Gain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GainMapper {

    @Mapping(source = "scoreGain", target = "scoreMin")
    GainDTO toDTO(Gain gain);

    @Mapping(source = "scoreMin", target = "scoreGain")
    @Mapping(target = "ressources", ignore = true)
    Gain toEntity(GainDTO gainDTO);

    List<GainDTO> toDTOList(List<Gain> gains);

    List<Gain> toEntityList(List<GainDTO> gainDTOs);

    @Mapping(source = "scoreMin", target = "scoreGain")
    @Mapping(target = "idGain", ignore = true)
    @Mapping(target = "ressources", ignore = true)
    void updateEntityFromDTO(GainDTO dto, @MappingTarget Gain entity);
}
