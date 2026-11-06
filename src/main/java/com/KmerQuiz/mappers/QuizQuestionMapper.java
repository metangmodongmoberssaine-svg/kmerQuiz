package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.QuizQuestionDTO;
import com.KmerQuiz.entities.QuizQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizQuestionMapper {

    @Mapping(source = "idQuizQquestion.idQuiz", target = "idQuiz")
    @Mapping(source = "idQuizQquestion.idQquestion", target = "idQuestion")
    @Mapping(source = "status", target = "Status")
    QuizQuestionDTO toDTO(QuizQuestion quizQuestion);

    @Mapping(source = "idQuiz", target = "idQuizQquestion.idQuiz")
    @Mapping(source = "idQuestion", target = "idQuizQquestion.idQquestion")
    @Mapping(source = "Status", target = "status")
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    QuizQuestion toEntity(QuizQuestionDTO dto);

    List<QuizQuestionDTO> toDTOList(List<QuizQuestion> quizQuestions);

    List<QuizQuestion> toEntityList(List<QuizQuestionDTO> dtos);
}
