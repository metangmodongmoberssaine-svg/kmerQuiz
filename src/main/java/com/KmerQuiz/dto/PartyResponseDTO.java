package com.KmerQuiz.dto;

import com.KmerQuiz.utils.PlayStatus;

import java.util.Date;

public record PartyResponseDTO(
        String codeParty,
        Date creationDate,
        int maxPlayers,
        PlayStatus partyStatus
) {
}
