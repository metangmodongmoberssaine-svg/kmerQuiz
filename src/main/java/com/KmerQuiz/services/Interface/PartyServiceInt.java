package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.PartyRequestDTO;
import com.KmerQuiz.dto.PartyResponseDTO;
import com.KmerQuiz.utils.PlayStatus;

import java.util.List;

public interface PartyServiceInt {
    PartyResponseDTO createParty(PartyRequestDTO requestDTO);
    PartyResponseDTO updateParty(Long codeParty, PartyRequestDTO requestDTO);
    PartyResponseDTO getPartyById(Long codeParty);
    List<PartyResponseDTO> getAllParties();
    List<PartyResponseDTO> getPartiesByStatus(PlayStatus status);
    PartyResponseDTO updatePartyStatus(Long codeParty, PlayStatus status);
    void deleteParty(Long codeParty);
}
