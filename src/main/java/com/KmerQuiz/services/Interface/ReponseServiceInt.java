package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.QuestionResponseDTO;
import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;

import java.util.List;

public interface ReponseServiceInt {
    ResponseRequestDTO save(ResponseRequestDTO questionResponseDTO);

    ResponseResponseDTO create(QuestionResponseDTO questionResponseDTO);

    ResponseResponseDTO update(QuestionResponseDTO questionResponseDTO);

    ResponseResponseDTO getResponseById(Long idResponse);

    List<ResponseResponseDTO> getResponsesByQuestion(Long idQuestion);

    List<ResponseResponseDTO> getCorrectResponsesByQuestion(Long idQuestion);

    void deleteById(Long idResponse);
}
