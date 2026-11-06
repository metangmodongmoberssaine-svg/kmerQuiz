package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.RessourceRequestDTO;
import com.KmerQuiz.dto.RessourceResponseDTO;
import com.KmerQuiz.entities.Gain;
import com.KmerQuiz.entities.Question;
import com.KmerQuiz.entities.Ressource;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.RessourceMapper;
import com.KmerQuiz.repositories.GainRepository;
import com.KmerQuiz.repositories.QuestionRepository;
import com.KmerQuiz.repositories.RessourceRepository;
import com.KmerQuiz.services.Interface.RessourceServiceInt;
import com.KmerQuiz.utils.TypeRessource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RessourceService implements RessourceServiceInt {

    private final RessourceRepository ressourceRepository;
    private final QuestionRepository questionRepository;
    private final GainRepository gainRepository;
    private final RessourceMapper ressourceMapper;

    public RessourceService(
            RessourceRepository ressourceRepository,
            QuestionRepository questionRepository,
            GainRepository gainRepository,
            RessourceMapper ressourceMapper
    ) {
        this.ressourceRepository = ressourceRepository;
        this.questionRepository = questionRepository;
        this.gainRepository = gainRepository;
        this.ressourceMapper = ressourceMapper;
    }

    @Override
    public RessourceResponseDTO createRessource(RessourceRequestDTO requestDTO) {
        Question question = null;
        if (requestDTO.idQuestion() != null) {
            question = questionRepository.findById(requestDTO.idQuestion())
                    .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", requestDTO.idQuestion()));
        }

        Gain gain = null;
        if (requestDTO.idGain() != null) {
            gain = gainRepository.findById(requestDTO.idGain())
                    .orElseThrow(() -> new RessourceNotFoundException("Gain", "idGain", requestDTO.idGain()));
        }

        Ressource ressource = ressourceMapper.toEntity(requestDTO);
        ressource.setQuestion(question);
        ressource.setGain(gain);

        Ressource saved = ressourceRepository.save(ressource);
        return ressourceMapper.toResponseDTO(saved);
    }

    @Override
    public RessourceResponseDTO updateRessource(Long idRessources, RessourceRequestDTO requestDTO) {
        Ressource existing = ressourceRepository.findById(idRessources)
                .orElseThrow(() -> new RessourceNotFoundException("Ressource", "idRessources", idRessources));

        Question question = null;
        if (requestDTO.idQuestion() != null) {
            question = questionRepository.findById(requestDTO.idQuestion())
                    .orElseThrow(() -> new RessourceNotFoundException("Question", "idQuestion", requestDTO.idQuestion()));
        }

        Gain gain = null;
        if (requestDTO.idGain() != null) {
            gain = gainRepository.findById(requestDTO.idGain())
                    .orElseThrow(() -> new RessourceNotFoundException("Gain", "idGain", requestDTO.idGain()));
        }

        ressourceMapper.updateEntityFromDTO(requestDTO, existing);
        existing.setQuestion(question);
        existing.setGain(gain);

        Ressource updated = ressourceRepository.save(existing);
        return ressourceMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public RessourceResponseDTO getRessourceById(Long idRessources) {
        Ressource ressource = ressourceRepository.findById(idRessources)
                .orElseThrow(() -> new RessourceNotFoundException("Ressource", "idRessources", idRessources));
        return ressourceMapper.toResponseDTO(ressource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RessourceResponseDTO> getAllRessources() {
        return ressourceMapper.toResponseDTOList(ressourceRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RessourceResponseDTO> getRessourcesByType(TypeRessource typeRessource) {
        return ressourceMapper.toResponseDTOList(ressourceRepository.findByTypeRessource(typeRessource));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RessourceResponseDTO> getRessourcesByQuestion(Long idQuestion) {
        return ressourceMapper.toResponseDTOList(ressourceRepository.findByQuestion_IdQuestion(idQuestion));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RessourceResponseDTO> getRessourcesByGain(Long idGain) {
        return ressourceMapper.toResponseDTOList(ressourceRepository.findByGain_IdGain(idGain));
    }

    @Override
    public void deleteRessource(Long idRessources) {
        if (!ressourceRepository.existsById(idRessources)) {
            throw new RessourceNotFoundException("Ressource", "idRessources", idRessources);
        }
        ressourceRepository.deleteById(idRessources);
    }
}
