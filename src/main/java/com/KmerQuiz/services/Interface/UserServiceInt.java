package com.KmerQuiz.services.Interface;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;

import java.util.List;

public interface UserServiceInt {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO updateUser(String idUser, UserRequestDTO dto);
    UserResponseDTO getUserById(String idUser);
    List<UserResponseDTO> findAllUsers();
    UserResponseDTO findUserByUserToken(String pseudo);
    List<UserResponseDTO> searchUsers(String token);
    void deleteUser(String idUser);
}