package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.UserGame;
import com.KmerQuiz.entities.Gain;
import com.KmerQuiz.entities.IdUserGain;
import com.KmerQuiz.entities.User;
import com.KmerQuiz.entities.UserGain;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.UserGainMapper;
import com.KmerQuiz.repositories.GainRepository;
import com.KmerQuiz.repositories.UserGainRepository;
import com.KmerQuiz.repositories.UserRepository;
import com.KmerQuiz.services.Interface.UserGainServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserGainService implements UserGainServiceInt {

    private final UserGainRepository userGainRepository;
    private final UserRepository userRepository;
    private final GainRepository gainRepository;
    private final UserGainMapper userGainMapper;

    public UserGainService(
            UserGainRepository userGainRepository,
            UserRepository userRepository,
            GainRepository gainRepository,
            UserGainMapper userGainMapper
    ) {
        this.userGainRepository = userGainRepository;
        this.userRepository = userRepository;
        this.gainRepository = gainRepository;
        this.userGainMapper = userGainMapper;
    }

    @Override
    public UserGame awardGainToUser(String idUser, Long idGain) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));

        Gain gain = gainRepository.findById(idGain)
                .orElseThrow(() -> new RessourceNotFoundException("Gain", "idGain", idGain));

        IdUserGain id = new IdUserGain(idUser, idGain);
        if (userGainRepository.existsById(id)) {
            throw new DuplicatedRessourcException("User already received this gain");
        }

        UserGain userGain = new UserGain();
        userGain.setIdUserGain(id);
        userGain.setUser(user);
        userGain.setGain(gain);
        userGain.setGettingDate(new Date());

        UserGain saved = userGainRepository.save(userGain);
        return userGainMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserGame> getGainsByUser(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new RessourceNotFoundException("User", "idUser", idUser);
        }
        return userGainMapper.toDTOList(userGainRepository.findByIdUserGain_IdUser(idUser));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserGame> getUsersByGain(Long idGain) {
        if (!gainRepository.existsById(idGain)) {
            throw new RessourceNotFoundException("Gain", "idGain", idGain);
        }
        return userGainMapper.toDTOList(userGainRepository.findByIdUserGain_IdGain(idGain));
    }

    @Override
    public void revokeGainFromUser(String idUser, Long idGain) {
        IdUserGain id = new IdUserGain(idUser, idGain);
        if (!userGainRepository.existsById(id)) {
            throw new RessourceNotFoundException("UserGain association not found for user " + idUser + " and gain " + idGain);
        }
        userGainRepository.deleteById(id);
    }
}
