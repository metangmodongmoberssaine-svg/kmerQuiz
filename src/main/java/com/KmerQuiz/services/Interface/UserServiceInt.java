package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UserServiceInt {
    UserResponseDTO registerUser(UserRequestDTO requestDTO);
    UserResponseDTO updateUser(String idUser, UserRequestDTO requestDTO);
    UserResponseDTO getUserById(String idUser);
    UserResponseDTO getUserByEmail(String email);
    UserResponseDTO getUserByPseudo(String pseudo);
    List<UserResponseDTO> getAllUsers();
    List<UserResponseDTO> searchUsers(String token);
    void deleteUser(String idUser);
    boolean verifyCredentials(String emailOrPhone, String password);

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO createUser(@Valid UserRequestDTO userRequestDTO);
}
