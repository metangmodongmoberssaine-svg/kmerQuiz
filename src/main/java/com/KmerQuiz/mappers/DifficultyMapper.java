package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.DifficultyDTO;
import com.KmerQuiz.dto.DifficultyRequestDTO;
import com.KmerQuiz.dto.DifficultyResponseDTO;
import com.KmerQuiz.entities.Difficulty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DifficultyMapper {
    DifficultyDTO toDTO(Difficulty difficulty);

    DifficultyResponseDTO toResponseDTO(Difficulty difficulty);

    @Mapping(target = "questions", ignore = true)
    Difficulty toEntity(DifficultyDTO difficultyDTO);

    @Mapping(target = "idDifficulty", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Difficulty toEntity(DifficultyRequestDTO requestDTO);

    List<DifficultyDTO> toDTOList(List<Difficulty> difficulties);

    List<DifficultyResponseDTO> toResponseDTOList(List<Difficulty> difficulties);

    @Mapping(target = "idDifficulty", ignore = true)
    @Mapping(target = "questions", ignore = true)
    void updateEntityFromDTO(DifficultyDTO dto, @MappingTarget Difficulty entity);
}
