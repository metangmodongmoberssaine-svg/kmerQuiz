package com.KmerQuiz.services.Implementation;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.entities.User;
import com.KmerQuiz.exceptions.DuplicatedRessourcException;
import com.KmerQuiz.exceptions.RessourceNotFoundException;
import com.KmerQuiz.mappers.UserMapper;
import com.KmerQuiz.repositories.UserRepository;
import com.KmerQuiz.services.Interface.UserServiceInt;
import com.KmerQuiz.utils.Generator;
import com.KmerQuiz.utils.RoleUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserServiceInt {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        String userId = requestDTO.idUser();
        if (userId == null || userId.isBlank()) {
            userId = Generator.generateUserId();
        }

        if (userRepository.existsById(userId)) {
            throw new DuplicatedRessourcException("User", "idUser", userId);
        }

        if (requestDTO.pseudoUser() != null && userRepository.existsByPseudoUser(requestDTO.pseudoUser())) {
            throw new DuplicatedRessourcException("User", "pseudoUser", requestDTO.pseudoUser());
        }

        if (requestDTO.emailUser() != null && userRepository.existsByEmailUser(requestDTO.emailUser())) {
            throw new DuplicatedRessourcException("User", "emailUser", requestDTO.emailUser());
        }

        if (requestDTO.phoneUser() != null && userRepository.existsByPhoneUser(requestDTO.phoneUser())) {
            throw new DuplicatedRessourcException("User", "phoneUser", requestDTO.phoneUser());
        }

        User user = userMapper.toEntity(requestDTO);
        user.setIdUser(userId);
        user.setRoleUser(RoleUser.Player);
        user.setInscriptionDataUser(new Date());

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(String idUser, UserRequestDTO requestDTO) {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));

        if (requestDTO.pseudoUser() != null && !requestDTO.pseudoUser().equalsIgnoreCase(existingUser.getPseudoUser())
                && userRepository.existsByPseudoUser(requestDTO.pseudoUser())) {
            throw new DuplicatedRessourcException("User", "pseudoUser", requestDTO.pseudoUser());
        }

        if (requestDTO.emailUser() != null && !requestDTO.emailUser().equalsIgnoreCase(existingUser.getEmailUser())
                && userRepository.existsByEmailUser(requestDTO.emailUser())) {
            throw new DuplicatedRessourcException("User", "emailUser", requestDTO.emailUser());
        }

        if (requestDTO.phoneUser() != null && !requestDTO.phoneUser().equalsIgnoreCase(existingUser.getPhoneUser())
                && userRepository.existsByPhoneUser(requestDTO.phoneUser())) {
            throw new DuplicatedRessourcException("User", "phoneUser", requestDTO.phoneUser());
        }

        userMapper.updateEntityFromDTO(requestDTO, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RessourceNotFoundException("User", "idUser", idUser));
        return userMapper.toResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmailUser(email)
                .orElseThrow(() -> new RessourceNotFoundException("User", "emailUser", email));
        return userMapper.toResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByPseudo(String pseudo) {
        User user = userRepository.findByPseudoUser(pseudo)
                .orElseThrow(() -> new RessourceNotFoundException("User", "pseudoUser", pseudo));
        return userMapper.toResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchUsers(String token) {
        return userMapper.toResponseDTOList(userRepository.findAllByPseudoUserContainingIgnoreCase(token));
    }

    @Override
    public void deleteUser(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new RessourceNotFoundException("User", "idUser", idUser);
        }
        userRepository.deleteById(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean verifyCredentials(String emailOrPhone, String password) {
        Optional<User> userOptional = userRepository.findByEmailUserOrPhoneUser(emailOrPhone, emailOrPhone);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPasswordUser() != null && user.getPasswordUser().equals(password);
        }
        return false;
    }
}
