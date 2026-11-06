package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.PartyRequestDTO;
import com.KmerQuiz.dto.PartyResponseDTO;
import com.KmerQuiz.entities.Party;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.PartyMapper;
import com.KmerQuiz.repositories.PartyRepository;
import com.KmerQuiz.services.Interface.PartyServiceInt;
import com.KmerQuiz.utils.PlayStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartyService implements PartyServiceInt {

    private final PartyRepository partyRepository;
    private final PartyMapper partyMapper;

    public PartyService(PartyRepository partyRepository, PartyMapper partyMapper) {
        this.partyRepository = partyRepository;
        this.partyMapper = partyMapper;
    }

    @Override
    public PartyResponseDTO createParty(PartyRequestDTO requestDTO) {
        Party party = partyMapper.toEntity(requestDTO);
        Party savedParty = partyRepository.save(party);
        return partyMapper.toResponseDTO(savedParty);
    }

    @Override
    public PartyResponseDTO updateParty(Long codeParty, PartyRequestDTO requestDTO) {
        Party existingParty = partyRepository.findById(codeParty)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", codeParty));

        partyMapper.updateEntityFromDTO(requestDTO, existingParty);
        Party updatedParty = partyRepository.save(existingParty);
        return partyMapper.toResponseDTO(updatedParty);
    }

    @Override
    @Transactional(readOnly = true)
    public PartyResponseDTO getPartyById(Long codeParty) {
        Party party = partyRepository.findById(codeParty)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", codeParty));
        return partyMapper.toResponseDTO(party);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDTO> getAllParties() {
        return partyMapper.toResponseDTOList(partyRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDTO> getPartiesByStatus(PlayStatus status) {
        return partyMapper.toResponseDTOList(partyRepository.findByPartyStatus(status));
    }

    @Override
    public PartyResponseDTO updatePartyStatus(Long codeParty, PlayStatus status) {
        Party party = partyRepository.findById(codeParty)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", codeParty));
        party.setPartyStatus(status);
        Party updatedParty = partyRepository.save(party);
        return partyMapper.toResponseDTO(updatedParty);
    }

    @Override
    public void deleteParty(Long codeParty) {
        if (!partyRepository.existsById(codeParty)) {
            throw new RessourceNotFoundException("Party", "codeParty", codeParty);
        }
        partyRepository.deleteById(codeParty);
    }
}
