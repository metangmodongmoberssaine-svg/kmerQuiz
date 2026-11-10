package com.KmerQuiz.controller;

import com.KmerQuiz.dto.RessourceRequestDTO;
import com.KmerQuiz.dto.RessourceResponseDTO;
import com.KmerQuiz.services.Interface.RessourceServiceInt;
import com.KmerQuiz.utils.TypeRessource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ressources")
@RequiredArgsConstructor
public class RessourceController {

    private final RessourceServiceInt ressourceService;

    // Créer une ressource (liée à une question ou à un gain)
    @PostMapping
    public ResponseEntity<RessourceResponseDTO> createRessource(@Valid @RequestBody RessourceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ressourceService.createRessource(requestDTO));
    }

    // Afficher toutes les ressources
    @GetMapping
    public ResponseEntity<List<RessourceResponseDTO>> findAll() {
        return ResponseEntity.ok(ressourceService.getAllRessources());
    }

    // Afficher une ressource
    @GetMapping("/{idRessources}")
    public ResponseEntity<RessourceResponseDTO> findById(@PathVariable Long idRessources) {
        return ResponseEntity.ok(ressourceService.getRessourceById(idRessources));
    }

    // Ressources par type
    @GetMapping("/type/{typeRessource}")
    public ResponseEntity<List<RessourceResponseDTO>> findByType(@PathVariable TypeRessource typeRessource) {
        return ResponseEntity.ok(ressourceService.getRessourcesByType(typeRessource));
    }

    // Ressources d'une question
    @GetMapping("/question/{idQuestion}")
    public ResponseEntity<List<RessourceResponseDTO>> findByQuestion(@PathVariable Long idQuestion) {
        return ResponseEntity.ok(ressourceService.getRessourcesByQuestion(idQuestion));
    }

    // Ressources d'un gain
    @GetMapping("/gain/{idGain}")
    public ResponseEntity<List<RessourceResponseDTO>> findByGain(@PathVariable Long idGain) {
        return ResponseEntity.ok(ressourceService.getRessourcesByGain(idGain));
    }

    // Modifier une ressource
    @PutMapping("/{idRessources}")
    public ResponseEntity<RessourceResponseDTO> updateRessource(
            @PathVariable Long idRessources,
            @Valid @RequestBody RessourceRequestDTO requestDTO) {
        return ResponseEntity.ok(ressourceService.updateRessource(idRessources, requestDTO));
    }

    // Supprimer une ressource
    @DeleteMapping("/{idRessources}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long idRessources) {
        ressourceService.deleteRessource(idRessources);
        return ResponseEntity.noContent().build();
    }
}
