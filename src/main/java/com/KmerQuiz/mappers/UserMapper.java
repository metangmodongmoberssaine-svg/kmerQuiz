package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserResponseDTO toDTO(User user);

    User toEntity(UserRequestDTO requestDTO);

    List<UserResponseDTO> toDTOList(List<User> users);
    List<User> toEntityList(List<UserRequestDTO> userRequestDTOS);
}
