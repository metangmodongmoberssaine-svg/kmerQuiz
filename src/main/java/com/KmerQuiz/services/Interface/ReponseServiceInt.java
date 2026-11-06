package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;

import java.util.List;

public interface ReponseServiceInt {
    ResponseResponseDTO createResponse(ResponseRequestDTO requestDTO);
    ResponseResponseDTO updateResponse(Long idResponse, ResponseRequestDTO requestDTO);
    ResponseResponseDTO getResponseById(Long idResponse);
    List<ResponseResponseDTO> getResponsesByQuestion(Long idQuestion);
    List<ResponseResponseDTO> getCorrectResponsesByQuestion(Long idQuestion);
    void deleteResponse(Long idResponse);
}
