package com.KmerQuiz.controller;

import com.KmerQuiz.dto.PartyRequestDTO;
import com.KmerQuiz.dto.PartyResponseDTO;
import com.KmerQuiz.services.Interface.PartyServiceInt;
import com.KmerQuiz.utils.PlayStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parties")
@RequiredArgsConstructor
public class PartyController {

    private final PartyServiceInt partyService;

    // Créer une partie
    @PostMapping
    public ResponseEntity<PartyResponseDTO> createParty(@Valid @RequestBody PartyRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partyService.createParty(requestDTO));
    }

    // Afficher toutes les parties
    @GetMapping
    public ResponseEntity<List<PartyResponseDTO>> findAll() {
        return ResponseEntity.ok(partyService.getAllParties());
    }

    // Afficher une partie
    @GetMapping("/{codeParty}")
    public ResponseEntity<PartyResponseDTO> findById(@PathVariable Long codeParty) {
        return ResponseEntity.ok(partyService.getPartyById(codeParty));
    }

    // Parties par statut : Not_STARTED, In_PROGRESS ou COMPLETED
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PartyResponseDTO>> findByStatus(@PathVariable PlayStatus status) {
        return ResponseEntity.ok(partyService.getPartiesByStatus(status));
    }

    // Modifier une partie
    @PutMapping("/{codeParty}")
    public ResponseEntity<PartyResponseDTO> updateParty(
            @PathVariable Long codeParty,
            @Valid @RequestBody PartyRequestDTO requestDTO) {
        return ResponseEntity.ok(partyService.updateParty(codeParty, requestDTO));
    }

    // Changer le statut : PATCH /{codeParty}/status?status=In_PROGRESS
    @PatchMapping("/{codeParty}/status")
    public ResponseEntity<PartyResponseDTO> updateStatus(
            @PathVariable Long codeParty,
            @RequestParam PlayStatus status) {
        return ResponseEntity.ok(partyService.updatePartyStatus(codeParty, status));
    }

    // Supprimer une partie
    @DeleteMapping("/{codeParty}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long codeParty) {
        partyService.deleteParty(codeParty);
        return ResponseEntity.noContent().build();
    }
}
