package com.KmerQuiz.repositories;

import com.KmerQuiz.entities.Gain;
import com.KmerQuiz.utils.TypeGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GainRepository extends JpaRepository<Gain, Long> {
    List<Gain> findByTypeGain(TypeGain typeGain);
    List<Gain> findByScoreGainLessThanEqual(double scoreGain);
    List<Gain> findByScoreGainGreaterThanEqual(double scoreGain);
}
