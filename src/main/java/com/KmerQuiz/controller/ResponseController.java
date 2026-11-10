package com.KmerQuiz.controller;

import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.dto.ResponseResponseDTO;
import com.KmerQuiz.services.Interface.ReponseServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ReponseServiceInt responseService;

    // Ajouter une réponse à une question existante
    @PostMapping
    public ResponseEntity<ResponseResponseDTO> createResponse(@Valid @RequestBody ResponseRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.createResponse(requestDTO));
    }

    // Afficher une réponse
    @GetMapping("/{idResponse}")
    public ResponseEntity<ResponseResponseDTO> findById(@PathVariable Long idResponse) {
        return ResponseEntity.ok(responseService.getResponseById(idResponse));
    }

    // Toutes les réponses d'une question
    @GetMapping("/question/{idQuestion}")
    public ResponseEntity<List<ResponseResponseDTO>> findByQuestion(@PathVariable Long idQuestion) {
        return ResponseEntity.ok(responseService.getResponsesByQuestion(idQuestion));
    }

    // Les bonnes réponses d'une question
    @GetMapping("/question/{idQuestion}/correct")
    public ResponseEntity<List<ResponseResponseDTO>> findCorrectByQuestion(@PathVariable Long idQuestion) {
        return ResponseEntity.ok(responseService.getCorrectResponsesByQuestion(idQuestion));
    }

    // Modifier une réponse
    @PutMapping("/{idResponse}")
    public ResponseEntity<ResponseResponseDTO> updateResponse(
            @PathVariable Long idResponse,
            @Valid @RequestBody ResponseRequestDTO requestDTO) {
        return ResponseEntity.ok(responseService.updateResponse(idResponse, requestDTO));
    }

    // Supprimer une réponse
    @DeleteMapping("/{idResponse}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long idResponse) {
        responseService.deleteResponse(idResponse);
        return ResponseEntity.noContent().build();
    }
}
