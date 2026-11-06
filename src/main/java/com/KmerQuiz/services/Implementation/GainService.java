package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.entities.Gain;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.GainMapper;
import com.KmerQuiz.repositories.GainRepository;
import com.KmerQuiz.repositories.RessourceRepository;
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
    private final RessourceRepository ressourceRepository;

    // Injection des dépendances par constructeur
    public GainService(
            GainRepository gainRepository,
            GainMapper gainMapper,
            RessourceRepository ressourceRepository) {

        this.gainRepository = gainRepository;
        this.gainMapper = gainMapper;
        this.ressourceRepository = ressourceRepository;
    }

    @Override
    public GainDTO save(GainDTO gainDTO) {
        Gain gain = gainMapper.toEntity(gainDTO);
        Gain savedGain = gainRepository.save(gain);
        return gainMapper.toDTO(savedGain);
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
                .orElseThrow(() ->
                        new RessourceNotFoundException("Gain", "idGain", idGain));

        gainMapper.updateEntityFromDTO(gainDTO, existingGain);

        Gain updatedGain = gainRepository.save(existingGain);

        return gainMapper.toDTO(updatedGain);
    }

    @Override
    public GainDTO findById(Long idGain) {
        return null;
    }

    @Override
    public void deleteByAllGains(String labelGain) {

    }


    @Transactional(readOnly = true)
    public GainDTO getGainById(Long idGain) {
        Gain gain = gainRepository.findById(idGain)
                .orElseThrow(() ->
                        new RessourceNotFoundException("Gain", "idGain", idGain));

        return gainMapper.toDTO(gain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GainDTO> getAllGains() {
        return gainMapper.toDTOList(gainRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<GainDTO> getGainsByType(TypeGain typeGain) {
        return gainMapper.toDTOList(
                gainRepository.findByTypeGain(typeGain));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GainDTO> getGainsEligibleForScore(double score) {
        return gainMapper.toDTOList(
                gainRepository.findByScoreGainLessThanEqual(score));
    }

    @Override
    public void deleteGain(Long idGain) {
        if (!gainRepository.existsById(idGain)) {
            throw new RessourceNotFoundException("Gain", "idGain", idGain);
        }

        gainRepository.deleteById(idGain);
    }

    @Override
    public List<GainDTO> getAllGain() {
        return List.of();
    }

    @Override
    public List<GainDTO> getGainByTypeGain(String typeGain) {
        return List.of();
    }

    @Override
    public List<ResponseResponseDTO> getAllRessourceByGain(Long idGain) {
        return List.of();
    }
}