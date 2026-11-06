package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.entities.Gain;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.GainMapper;
import com.KmerQuiz.repositories.GainRepository;
import com.KmerQuiz.services.Interface.GainServiceInt;
import com.KmerQuiz.utils.TypeGain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GainService implements GainServiceInt {

    private final GainRepository gainRepository;
    private final GainMapper gainMapper;

    public GainService(GainRepository gainRepository, GainMapper gainMapper) {
        this.gainRepository = gainRepository;
        this.gainMapper = gainMapper;
    }

    @Override
    public GainDTO createGain(GainDTO gainDTO) {
        Gain gain = gainMapper.toEntity(gainDTO);
        Gain savedGain = gainRepository.save(gain);
        return gainMapper.toDTO(savedGain);
    }

    @Override
    public GainDTO updateGain(Long idGain, GainDTO gainDTO) {
        Gain existingGain = gainRepository.findById(idGain)
                .orElseThrow(() -> new RessourceNotFoundException("Gain", "idGain", idGain));
        gainMapper.updateEntityFromDTO(gainDTO, existingGain);
        Gain updatedGain = gainRepository.save(existingGain);
        return gainMapper.toDTO(updatedGain);
    }

    @Override
    @Transactional(readOnly = true)
    public GainDTO getGainById(Long idGain) {
        Gain gain = gainRepository.findById(idGain)
                .orElseThrow(() -> new RessourceNotFoundException("Gain", "idGain", idGain));
        return gainMapper.toDTO(gain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GainDTO> getAllGains() {
        return gainMapper.toDTOList(gainRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GainDTO> getGainsByType(TypeGain typeGain) {
        return gainMapper.toDTOList(gainRepository.findByTypeGain(typeGain));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GainDTO> getGainsEligibleForScore(double score) {
        return gainMapper.toDTOList(gainRepository.findByScoreGainLessThanEqual(score));
    }

    @Override
    public void deleteGain(Long idGain) {
        if (!gainRepository.existsById(idGain)) {
            throw new RessourceNotFoundException("Gain", "idGain", idGain);
        }
        gainRepository.deleteById(idGain);
    }
}
