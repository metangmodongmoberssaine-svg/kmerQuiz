package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "inscriptionDataUser", expression = "java(new java.util.Date())")
    @Mapping(target = "roleUser", expression = "java(com.KmerQuiz.utils.RoleUser.Player)")
    @Mapping(target = "quizList", ignore = true)
    User toEntity(UserRequestDTO requestDTO);

    List<UserResponseDTO> toResponseDTOList(List<User> users);

    // Les champs null du DTO n'écrasent pas les valeurs existantes
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "inscriptionDataUser", ignore = true)
    @Mapping(target = "roleUser", ignore = true)
    @Mapping(target = "quizList", ignore = true)
    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User entity);
}
