package com.KmerQuiz.controller;

import com.KmerQuiz.dto.QuizQuestionDTO;
import com.KmerQuiz.dto.QuizRequestDTO;
import com.KmerQuiz.dto.QuizResponseDTO;
import com.KmerQuiz.services.Interface.QuizQuestionServiceInt;
import com.KmerQuiz.services.Interface.QuizServiceInt;
import com.KmerQuiz.utils.PlayStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizServiceInt quizService;
    private final QuizQuestionServiceInt quizQuestionService;



    // Créer un quiz (le créateur idUser et la partie codeParty doivent exister)
    @PostMapping
    public ResponseEntity<QuizResponseDTO> createQuiz(@Valid @RequestBody QuizRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(requestDTO));
    }

    // Afficher tous les quiz
    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> findAll() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    // Afficher un quiz par son id
    @GetMapping("/{idQuiz}")
    public ResponseEntity<QuizResponseDTO> findById(@PathVariable Long idQuiz) {
        return ResponseEntity.ok(quizService.getQuizById(idQuiz));
    }

    // Quiz créés par un utilisateur
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<QuizResponseDTO>> findByUser(@PathVariable String idUser) {
        return ResponseEntity.ok(quizService.getQuizzesByUser(idUser));
    }

    // Quiz d'une partie
    @GetMapping("/party/{codeParty}")
    public ResponseEntity<List<QuizResponseDTO>> findByParty(@PathVariable Long codeParty) {
        return ResponseEntity.ok(quizService.getQuizzesByParty(codeParty));
    }

    // Quiz par statut : Not_STARTED, In_PROGRESS ou COMPLETED
    @GetMapping("/status/{status}")
    public ResponseEntity<List<QuizResponseDTO>> findByStatus(@PathVariable PlayStatus status) {
        return ResponseEntity.ok(quizService.getQuizzesByStatus(status));
    }

    // Modifier un quiz
    @PutMapping("/{idQuiz}")
    public ResponseEntity<QuizResponseDTO> updateQuiz(
            @PathVariable Long idQuiz,
            @Valid @RequestBody QuizRequestDTO requestDTO) {
        return ResponseEntity.ok(quizService.updateQuiz(idQuiz, requestDTO));
    }

    // Valider / invalider un quiz : PATCH /{idQuiz}/validate?valid=true
    @PatchMapping("/{idQuiz}/validate")
    public ResponseEntity<QuizResponseDTO> validateQuiz(
            @PathVariable Long idQuiz,
            @RequestParam(defaultValue = "true") boolean valid) {
        return ResponseEntity.ok(quizService.validateQuiz(idQuiz, valid));
    }

    // Supprimer un quiz
    @DeleteMapping("/{idQuiz}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long idQuiz) {
        quizService.deleteQuiz(idQuiz);
        return ResponseEntity.noContent().build();
    }


    // Ajouter une question existante à un quiz
    @PostMapping("/{idQuiz}/questions/{idQuestion}")
    public ResponseEntity<QuizQuestionDTO> addQuestion(
            @PathVariable Long idQuiz,
            @PathVariable Long idQuestion,
            @RequestParam(defaultValue = "true") boolean status) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quizQuestionService.addQuestionToQuiz(idQuiz, idQuestion, status));
    }

    // Lister les questions d'un quiz
    @GetMapping("/{idQuiz}/questions")
    public ResponseEntity<List<QuizQuestionDTO>> getQuestions(@PathVariable Long idQuiz) {
        return ResponseEntity.ok(quizQuestionService.getQuestionsByQuiz(idQuiz));
    }

    // Changer le statut d'une question dans un quiz
    @PatchMapping("/{idQuiz}/questions/{idQuestion}/status")
    public ResponseEntity<QuizQuestionDTO> updateQuestionStatus(
            @PathVariable Long idQuiz,
            @PathVariable Long idQuestion,
            @RequestParam boolean status) {
        return ResponseEntity.ok(quizQuestionService.updateQuizQuestionStatus(idQuiz, idQuestion, status));
    }

    // Retirer une question d'un quiz
    @DeleteMapping("/{idQuiz}/questions/{idQuestion}")
    public ResponseEntity<Void> removeQuestion(
            @PathVariable Long idQuiz,
            @PathVariable Long idQuestion) {
        quizQuestionService.removeQuestionFromQuiz(idQuiz, idQuestion);
        return ResponseEntity.noContent().build();
    }
}
