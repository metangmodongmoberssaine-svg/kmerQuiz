package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
    Optional<Difficulty> findByLabelDifficultyIgnoreCase(String labelDifficulty);
    boolean existsByLabelDifficultyIgnoreCase(String labelDifficulty);
}
