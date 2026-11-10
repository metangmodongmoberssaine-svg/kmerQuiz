package com.KmerQuiz.controller;

import com.KmerQuiz.dto.QuestionRequestDTO;
import com.KmerQuiz.dto.QuestionResponseDTO;
import com.KmerQuiz.dto.ResponseRequestDTO;
import com.KmerQuiz.services.Implementation.QuestionResponseFactoryService;
import com.KmerQuiz.services.Interface.QuestionServiceInt;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceInt questionService;
    private final QuestionResponseFactoryService questionResponseFactoryService;

    // Une réponse proposée (l'idQuestion n'est pas nécessaire : la question est créée en même temps)
    public record ResponseOption(
            @NotBlank String labelReponse,
            @NotNull Boolean isCorrect
    ) {
    }

    // Corps de la création "question + réponses"
    public record QuestionWithResponsesRequest(
            @NotNull @Valid QuestionRequestDTO question,
            @NotEmpty @Valid List<ResponseOption> responses
    ) {
    }

    // Créer une question seule
    @PostMapping
    public ResponseEntity<QuestionResponseDTO> createQuestion(@Valid @RequestBody QuestionRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(requestDTO));
    }

    // Créer une question avec toutes ses réponses en une seule fois
    @PostMapping("/with-responses")
    public ResponseEntity<QuestionResponseDTO> createQuestionWithResponses(
            @Valid @RequestBody QuestionWithResponsesRequest request) {

        List<ResponseRequestDTO> responses = request.responses().stream()
                .map(option -> new ResponseRequestDTO(option.labelReponse(), option.isCorrect(), null))
                .toList();

        QuestionResponseDTO created =
                questionResponseFactoryService.createQuestionWithResponses(request.question(), responses);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Afficher toutes les questions
    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> findAll() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    // Afficher une question
    @GetMapping("/{idQuestion}")
    public ResponseEntity<QuestionResponseDTO> findById(@PathVariable Long idQuestion) {
        return ResponseEntity.ok(questionService.getQuestionById(idQuestion));
    }

    // Questions d'un niveau de difficulté
    @GetMapping("/difficulty/{idDifficulty}")
    public ResponseEntity<List<QuestionResponseDTO>> findByDifficulty(@PathVariable Long idDifficulty) {
        return ResponseEntity.ok(questionService.getQuestionsByDifficulty(idDifficulty));
    }

    // Questions d'une catégorie
    @GetMapping("/category/{idCategory}")
    public ResponseEntity<List<QuestionResponseDTO>> findByCategory(@PathVariable Long idCategory) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(idCategory));
    }

    // Modifier une question
    @PutMapping("/{idQuestion}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion(
            @PathVariable Long idQuestion,
            @Valid @RequestBody QuestionRequestDTO requestDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(idQuestion, requestDTO));
    }

    // Supprimer une question
    @DeleteMapping("/{idQuestion}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long idQuestion) {
        questionService.deleteQuestion(idQuestion);
        return ResponseEntity.noContent().build();
    }
}
