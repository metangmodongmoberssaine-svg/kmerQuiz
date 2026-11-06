package com.KmerQuiz.mappers;

import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.entities.Response;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    @Mapping(source = "idResponse", target = "idResponse")
    @Mapping(source = "question.idQuestion", target = "idQuestion")
    ResponseResponseDTO toResponseDTO(Response response);

    @Mapping(target = "idResponse", ignore = true)
    @Mapping(target = "question", ignore = true)
    Response toEntity(ResponseRequestDTO requestDTO);

    @Mapping(source = "idResponse", target = "idResponse")
    @Mapping(target = "question", ignore = true)
    Response toEntity(ResponseResponseDTO responseDTO);

    List<ResponseResponseDTO> toResponseDTOList(List<Response> responses);

    @Mapping(target = "idResponse", ignore = true)
    @Mapping(target = "question", ignore = true)
    void updateEntityFromDTO(ResponseRequestDTO dto, @MappingTarget Response entity);
}
