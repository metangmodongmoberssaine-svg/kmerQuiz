package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.QuizRequestDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.entities.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PartyMapper.class, QuizQuestionMapper.class})
public interface QuizMapper {

    @Mapping(source = "titreQuiz", target = "titleQuiz")
    QuizResponseDTO toResponseDTO(Quiz quiz);

    @Mapping(source = "titleQuiz", target = "titreQuiz")
    @Mapping(target = "idQuiz", ignore = true)
    @Mapping(target = "nberOfQuestionsQuiz", ignore = true)
    @Mapping(target = "quizStatus", ignore = true)
    @Mapping(target = "validQuiz", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "quizQuestions", ignore = true)
    @Mapping(target = "party", ignore = true)
    Quiz toEntity(QuizRequestDTO dto);

    List<QuizResponseDTO> toResponseDTOList(List<Quiz> quizzes);

    @Mapping(source = "titleQuiz", target = "titreQuiz")
    @Mapping(target = "idQuiz", ignore = true)
    @Mapping(target = "nberOfQuestionsQuiz", ignore = true)
    @Mapping(target = "quizStatus", ignore = true)
    @Mapping(target = "validQuiz", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "quizQuestions", ignore = true)
    @Mapping(target = "party", ignore = true)
    void updateEntityFromDTO(QuizRequestDTO dto, @MappingTarget Quiz entity);
}
