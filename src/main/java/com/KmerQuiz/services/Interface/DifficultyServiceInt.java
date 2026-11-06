package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.DifficultyDTO;
import com.KmerQuiz.dto.DifficultyRequestDTO;

import java.util.List;

public interface DifficultyServiceInt {
    DifficultyDTO createDifficulty(DifficultyRequestDTO requestDTO);
    DifficultyDTO updateDifficulty(Long idDifficulty, DifficultyDTO difficultyDTO);
    DifficultyDTO getDifficultyById(Long idDifficulty);
    List<DifficultyDTO> getAllDifficulties();
    void deleteDifficulty(Long idDifficulty);
}
