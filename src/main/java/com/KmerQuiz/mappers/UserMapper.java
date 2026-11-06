package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "idUser", target = "IdUser")
    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "inscriptionDataUser", expression = "java(new java.util.Date())")
    @Mapping(target = "roleUser", expression = "java(com.KmerQuiz.utils.RoleUser.Player)")
    @Mapping(target = "quizList", ignore = true)
    User toEntity(UserRequestDTO requestDTO);

    List<UserResponseDTO> toResponseDTOList(List<User> users);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "inscriptionDataUser", ignore = true)
    @Mapping(target = "roleUser", ignore = true)
    @Mapping(target = "quizList", ignore = true)
    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User entity);
}
