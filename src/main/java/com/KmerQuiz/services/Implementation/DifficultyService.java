package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.DifficultyDTO;
import com.KmerQuiz.dto.DifficultyRequestDTO;
import com.KmerQuiz.entities.Difficulty;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.DifficultyMapper;
import com.KmerQuiz.mappers.QuestionMapper;
import com.KmerQuiz.repositories.DifficultyRepository;
import com.KmerQuiz.services.Interface.DifficultyServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DifficultyService implements DifficultyServiceInt {

    private final DifficultyRepository difficultyRepository;
    private final DifficultyMapper difficultyMapper;
    private final QuestionMapper questionMapper;

    public DifficultyService(DifficultyRepository difficultyRepository, DifficultyMapper difficultyMapper) {
        this.difficultyRepository = difficultyRepository;
        this.difficultyMapper = difficultyMapper;
    }

    @Override
    @Transactional
    public DifficultyDTO createDifficulty(DifficultyRequestDTO requestDTO) {
        if (difficultyRepository.existsByLabelDifficultyIgnoreCase(requestDTO.labelDifficulty())) {
            throw new DuplicatedRessourcException("Difficulty", "labelDifficulty", requestDTO.labelDifficulty());
        }
        Difficulty difficulty = difficultyMapper.toEntity(requestDTO);
        Difficulty savedDifficulty = difficultyRepository.save(difficulty);
        return difficultyMapper.toDTO(savedDifficulty);
    }

    @Override
    @Transactional
    public DifficultyDTO updateDifficulty(Long idDifficulty, DifficultyDTO difficultyDTO) {
        Difficulty existingDifficulty = difficultyRepository.findById(idDifficulty)
                .orElseThrow(() -> new RessourceNotFoundException("Difficulty", "idDifficulty", idDifficulty));

        if (!existingDifficulty.getLabelDifficulty().equalsIgnoreCase(difficultyDTO.labelDifficulty()) &&
                difficultyRepository.existsByLabelDifficultyIgnoreCase(difficultyDTO.labelDifficulty())) {
            throw new DuplicatedRessourcException("Difficulty", "labelDifficulty", difficultyDTO.labelDifficulty());
        }

        difficultyMapper.updateEntityFromDTO(difficultyDTO, existingDifficulty);
        Difficulty updatedDifficulty = difficultyRepository.save(existingDifficulty);
        return difficultyMapper.toDTO(updatedDifficulty);
    }

    @Override
    @Transactional(readOnly = true)
    public DifficultyDTO getDifficultyById(Long idDifficulty) {
        Difficulty difficulty = difficultyRepository.findById(idDifficulty)
                .orElseThrow(() -> new RessourceNotFoundException("Difficulty", "idDifficulty", idDifficulty));
        return difficultyMapper.toDTO(difficulty);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DifficultyDTO> getAllDifficulties() {
        return difficultyMapper.toDTOList(difficultyRepository.findAll());
    }

    @Override
    public void deleteDifficulty(Long idDifficulty) {
        if (!difficultyRepository.existsById(idDifficulty)) {
            throw new RessourceNotFoundException("Difficulty", "idDifficulty", idDifficulty);
        }
        difficultyRepository.deleteById(idDifficulty);
    }
}
