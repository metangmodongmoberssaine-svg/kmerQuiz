package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.entities.Question;
import com.KmerQuiz.entities.Response;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.ResponseMapper;
import com.KmerQuiz.repositories.QuestionRepository;
import com.KmerQuiz.repositories.ResponseRepository;
import com.KmerQuiz.services.Interface.ReponseServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReponseService implements ReponseServiceInt {

    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;
    private final ResponseMapper responseMapper;

    public ReponseService(
            ResponseRepository responseRepository,
            QuestionRepository questionRepository,
            ResponseMapper responseMapper
    ) {
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
        this.responseMapper = responseMapper;
    }

    @Override
    public ResponseResponseDTO createResponse(ResponseRequestDTO requestDTO) {
        Question question = questionRepository.findById(requestDTO.idQuestion())
                .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", requestDTO.idQuestion()));

        Response response = responseMapper.toEntity(requestDTO);
        response.setQuestion(question);

        Response savedResponse = responseRepository.save(response);
        return responseMapper.toResponseDTO(savedResponse);
    }

    @Override
    public ResponseResponseDTO updateResponse(Long idResponse, ResponseRequestDTO requestDTO) {
        Response existingResponse = responseRepository.findById(idResponse)
                .orElseThrow(() -> new RessourceNotFoundException("Response", "idResponse", idResponse));

        Question question = questionRepository.findById(requestDTO.idQuestion())
                .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", requestDTO.idQuestion()));

        responseMapper.updateEntityFromDTO(requestDTO, existingResponse);
        existingResponse.setQuestion(question);

        Response updatedResponse = responseRepository.save(existingResponse);
        return responseMapper.toResponseDTO(updatedResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResponseDTO getResponseById(Long idResponse) {
        Response response = responseRepository.findById(idResponse)
                .orElseThrow(() -> new RessourceNotFoundException("Response", "idResponse", idResponse));
        return responseMapper.toResponseDTO(response);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseResponseDTO> getResponsesByQuestion(Long idQuestion) {
        return responseMapper.toResponseDTOList(responseRepository.findByQuestion_IdQuestion(idQuestion));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseResponseDTO> getCorrectResponsesByQuestion(Long idQuestion) {
        return responseMapper.toResponseDTOList(responseRepository.findByQuestion_IdQuestionAndIsCorrectTrue(idQuestion));
    }

    @Override
    public void deleteResponse(Long idResponse) {
        if (!responseRepository.existsById(idResponse)) {
            throw new RessourceNotFoundException("Response", "idResponse", idResponse);
        }
        responseRepository.deleteById(idResponse);
    }
}
