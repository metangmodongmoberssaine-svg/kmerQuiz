package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.QuestionResponseDTO;
import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, DifficultyMapper.class, ResponseMapper.class, RessourceMapper.class})
public interface QuestioMapper {

    @Mapping(target = "responses", ignore = true)
    QuestionResponseDTO toResponseDTO(Question question);

    @Mapping(target = "idQuestion", ignore = true)
    @Mapping(target = "quizQuestions", ignore = true)
    @Mapping(target = "difficulty", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "ressources", ignore = true)
    Question toEntity(QuestionRequestDTO dto);

    List<QuestionResponseDTO> toResponseDTOList(List<Question> questions);

    @Mapping(target = "idQuestion", ignore = true)
    @Mapping(target = "quizQuestions", ignore = true)
    @Mapping(target = "difficulty", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "ressources", ignore = true)
    void updateEntityFromDTO(QuestionRequestDTO dto, @MappingTarget Question entity);
}
