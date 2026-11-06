package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.UserPartyDTO;
import com.KmerQuiz.entities.IdUserParty;
import com.KmerQuiz.entities.Party;
import com.KmerQuiz.entities.User;
import com.KmerQuiz.entities.UserParty;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.UserPartyMapper;
import com.KmerQuiz.repositories.PartyRepository;
import com.KmerQuiz.repositories.UserPartyRepository;
import com.KmerQuiz.repositories.UserRepository;
import com.KmerQuiz.services.Interface.UserPartyServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserPartyService implements UserPartyServiceInt {

    private final UserPartyRepository userPartyRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final UserPartyMapper userPartyMapper;

    public UserPartyService(
            UserPartyRepository userPartyRepository,
            UserRepository userRepository,
            PartyRepository partyRepository,
            UserPartyMapper userPartyMapper
    ) {
        this.userPartyRepository = userPartyRepository;
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
        this.userPartyMapper = userPartyMapper;
    }

    @Override
    public UserPartyDTO joinParty(String idUser, Long codeParty) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));

        Party party = partyRepository.findById(codeParty)
                .orElseThrow(() -> new RessourceNotFoundException("Party", "codeParty", codeParty));

        IdUserParty id = new IdUserParty(idUser, codeParty);
        if (userPartyRepository.existsById(id)) {
            throw new DuplicatedRessourcException("User already joined party: " + codeParty);
        }

        List<UserParty> currentParticipants = userPartyRepository.findByIdUserParty_CodeParty(codeParty);
        if (currentParticipants.size() >= party.getMaxPlayers()) {
            throw new IllegalStateException("Party is already full (max: " + party.getMaxPlayers() + ")");
        }

        UserParty userParty = new UserParty();
        userParty.setIdUserParty(id);
        userParty.setUser(user);
        userParty.setParty(party);
        userParty.setScorePlayer(0.0);

        UserParty saved = userPartyRepository.save(userParty);
        return userPartyMapper.toDTO(saved);
    }

    @Override
    public UserPartyDTO updateScore(String idUser, Long codeParty, double score) {
        IdUserParty id = new IdUserParty(idUser, codeParty);
        UserParty userParty = userPartyRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("UserParty not found for user " + idUser + " and party " + codeParty));

        userParty.setScorePlayer(score);
        UserParty updated = userPartyRepository.save(userParty);
        return userPartyMapper.toDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPartyDTO> getPartyParticipants(Long codeParty) {
        if (!partyRepository.existsById(codeParty)) {
            throw new RessourceNotFoundException("Party", "codeParty", codeParty);
        }
        return userPartyMapper.toDTOList(userPartyRepository.findByIdUserParty_CodeParty(codeParty));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPartyDTO> getPartyLeaderboard(Long codeParty) {
        if (!partyRepository.existsById(codeParty)) {
            throw new RessourceNotFoundException("Party", "codeParty", codeParty);
        }
        return userPartyMapper.toDTOList(userPartyRepository.findByIdUserParty_CodePartyOrderByScorePlayerDesc(codeParty));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPartyDTO> getUserParties(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new RessourceNotFoundException("User", "idUser", idUser);
        }
        return userPartyMapper.toDTOList(userPartyRepository.findByIdUserParty_IdUser(idUser));
    }

    @Override
    public void leaveParty(String idUser, Long codeParty) {
        IdUserParty id = new IdUserParty(idUser, codeParty);
        if (!userPartyRepository.existsById(id)) {
            throw new RessourceNotFoundException("UserParty not found for user " + idUser + " and party " + codeParty);
        }
        userPartyRepository.deleteById(id);
    }
}
