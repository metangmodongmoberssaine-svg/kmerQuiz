package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.entities.User;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.PartyMapper;
import com.KmerQuiz.mappers.UserGainMapper;
import com.KmerQuiz.mappers.UserMapper;
import com.KmerQuiz.repositories.UserGainRepository;
import com.KmerQuiz.repositories.UserPartyRepository;
import com.KmerQuiz.repositories.UserRepository;
import com.KmerQuiz.services.Interface.UserServiceInt;
import com.KmerQuiz.utils.RoleUser;
import lombok.RequiredArgsConstructor;
import com.KmerQuiz.utils.Generator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServiceInt {

    private final UserRepository userRepository;
    private final Generator generator;
    private final UserMapper userMapper;
    private final PartyMapper partyMapper;
    private final UserGainMapper userGainMapper;
    private final UserGainRepository userGainRepository;
    private final UserPartyRepository userPartyRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByPseudoUser(dto.pseudoUser())) {
            throw new DuplicatedRessourcException("User", "pseudoUser", dto.pseudoUser());
        }
        if (dto.emailUser() != null && userRepository.existsByEmailUser(dto.emailUser())) {
            throw new DuplicatedRessourcException("User", "emailUser", dto.emailUser());
        }

        User user = userMapper.toEntity(dto);
        user.setIdUser("USR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        user.setPasswordUser(passwordEncoder.encode(dto.passwordUser()));
        user.setRoleUser(RoleUser.Player);          // jamais lu depuis la requete
        user.setStatusUser("ACTIVE");
        user.setInscriptionDataUser(new Date());

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(String idUser, UserRequestDTO dto) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));

        if (dto.pseudoUser() != null && !dto.pseudoUser().equals(user.getPseudoUser())) {
            if (userRepository.existsByPseudoUser(dto.pseudoUser())) {
                throw new DuplicatedRessourcException("User", "pseudoUser", dto.pseudoUser());
            }
            user.setPseudoUser(dto.pseudoUser());
        }
        if (dto.emailUser() != null)  user.setEmailUser(dto.emailUser());
        if (dto.phoneUser() != null)  user.setPhoneUser(dto.phoneUser());
        if (dto.avatarUser() != null) user.setAvatarUser(dto.avatarUser());
        if (dto.passwordUser() != null && !dto.passwordUser().isBlank()) {
            user.setPasswordUser(passwordEncoder.encode(dto.passwordUser()));
        }
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findUserByUserToken(String pseudo) {
        User user = userRepository.findByPseudoUser(pseudo)
                .orElseThrow(() -> new RessourceNotFoundException("User", "pseudoUser", pseudo));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchUsers(String token) {
        return userMapper.toDTOList(
                userRepository.findAllByPseudoUserContainingIgnoreCase(token));
    }

    @Override
    public void deleteUser(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new RessourceNotFoundException("User", "idUser", idUser);
        }
        userRepository.deleteById(idUser);
    }
}