package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.dto.QuestionResponseDTO;

import java.util.List;

public interface QuestionServiceInt {
    QuestionResponseDTO save(QuestionRequestDTO questionRequestDTO);
    QuestionResponseDTO update(QuestionRequestDTO questionRequestDTO);

    List<QuestionResponseDTO> FindAll();
    void deleteById(Long idQuestion);
    List<String> getAllLabelQuestion(Long idQuestion);

}
