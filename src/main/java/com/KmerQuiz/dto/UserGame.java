package com.KmerQuiz.dto;

import java.util.Date;

public record UserGame(
        String idUser,
        Long idGain,
        Date gettingDate,
        GainDTO gain
) {
}
