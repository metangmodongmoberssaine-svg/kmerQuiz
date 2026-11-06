package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.utils.TypeGain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GainServiceInt {
    GainDTO save(GainDTO gainDTO);
    GainDTO createGain(GainDTO gainDTO);
    GainDTO updateGain(Long idGain, GainDTO gainDTO);
    GainDTO findById(Long idGain);
    void  deleteByAllGains(String labelGain);

    @Transactional(readOnly = true)
    List<GainDTO> getAllGains();

    @Transactional(readOnly = true)
    List<GainDTO> getGainsByType(TypeGain typeGain);

    @Transactional(readOnly = true)
    List<GainDTO> getGainsEligibleForScore(double score);

    void deleteGain(Long idGain);
    List<GainDTO> getAllGain();
    List<GainDTO> getGainByTypeGain(String typeGain);
    List<ResponseResponseDTO> getAllRessourceByGain(Long idGain);

}
