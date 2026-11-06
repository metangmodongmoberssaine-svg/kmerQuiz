package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.utils.TypeGain;

import java.util.List;

public interface GainServiceInt {
    GainDTO createGain(GainDTO gainDTO);
    GainDTO updateGain(Long idGain, GainDTO gainDTO);
    GainDTO getGainById(Long idGain);
    List<GainDTO> getAllGains();
    List<GainDTO> getGainsByType(TypeGain typeGain);
    List<GainDTO> getGainsEligibleForScore(double score);
    void deleteGain(Long idGain);
}
