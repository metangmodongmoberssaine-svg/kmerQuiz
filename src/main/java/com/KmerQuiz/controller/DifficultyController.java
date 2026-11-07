package com.KmerQuiz.controller;

import com.KmerQuiz.dto.DifficultyDTO;
import com.KmerQuiz.dto.DifficultyRequestDTO;
import com.KmerQuiz.services.Interface.DifficultyServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/difficulties")
@RequiredArgsConstructor
public class DifficultyController {

    private final DifficultyServiceInt difficultyService;

    // Créer un niveau de difficulté
    @PostMapping
    public ResponseEntity<DifficultyDTO> createDifficulty(@Valid @RequestBody DifficultyRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(difficultyService.createDifficulty(requestDTO));
    }

    // Afficher tous les niveaux
    @GetMapping
    public ResponseEntity<List<DifficultyDTO>> findAll() {
        return ResponseEntity.ok(difficultyService.getAllDifficulties());
    }

    // Afficher un niveau
    @GetMapping("/{idDifficulty}")
    public ResponseEntity<DifficultyDTO> findById(@PathVariable Long idDifficulty) {
        return ResponseEntity.ok(difficultyService.getDifficultyById(idDifficulty));
    }

    // Modifier un niveau
    @PutMapping("/{idDifficulty}")
    public ResponseEntity<DifficultyDTO> updateDifficulty(
            @PathVariable Long idDifficulty,
            @Valid @RequestBody DifficultyDTO difficultyDTO) {
        return ResponseEntity.ok(difficultyService.updateDifficulty(idDifficulty, difficultyDTO));
    }

    @DeleteMapping("/{idDifficulty}")
    public ResponseEntity<Void> deleteDifficulty(@PathVariable Long idDifficulty) {
        difficultyService.deleteDifficulty(idDifficulty);
        return ResponseEntity.noContent().build();
    }
}
