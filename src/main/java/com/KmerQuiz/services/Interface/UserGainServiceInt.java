package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.UserGame;

import java.util.List;

public interface UserGainServiceInt {
    UserGame awardGainToUser(String idUser, Long idGain);
    List<UserGame> getGainsByUser(String idUser);
    List<UserGame> getUsersByGain(Long idGain);
    void revokeGainFromUser(String idUser, Long idGain);
}
