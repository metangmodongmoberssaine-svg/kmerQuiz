package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.PartyRequestDTO;
import com.KmerQuiz.dto.PartyResponseDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.utils.PlayStatus;

import java.util.List;

public interface PartyServiceInt {
    PartyResponseDTO save(PartyRequestDTO partyRequestDTO);
    PartyResponseDTO findById(String codeParty);
    PartyResponseDTO deleteById(String codeParty);
    PartyResponseDTO update(String codeParty, PartyRequestDTO partyRequestDTO);
    List<PartyResponseDTO> findAll();
    List<PartyResponseDTO> findAllQuizByIdParty(String codeParty);
    List<UserResponseDTO> findAllUserByParty(String codeParty);
    List<QuizResponseDTO> findAllQuizByParty(String codeParty);
    void deleteParty(String codeParty);


}
