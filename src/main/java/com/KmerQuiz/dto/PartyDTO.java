package com.KmerQuiz.dto;

import com.KmerQuiz.utils.PlayStatus;

import java.util.Date;

public record PartyDTO(
        String codeParty,
        Date createdDate,
        int maxPlayers,
        PlayStatus partyStatus
) {
}
