package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.UserPartyDTO;

import java.util.List;

public interface UserPartyServiceInt {
    UserPartyDTO joinParty(String idUser, Long codeParty);
    UserPartyDTO updateScore(String idUser, Long codeParty, double score);
    List<UserPartyDTO> getPartyParticipants(Long codeParty);
    List<UserPartyDTO> getPartyLeaderboard(Long codeParty);
    List<UserPartyDTO> getUserParties(String idUser);
    void leaveParty(String idUser, Long codeParty);
}
