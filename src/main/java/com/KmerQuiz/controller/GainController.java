package com.KmerQuiz.controller;

import com.KmerQuiz.dto.GainDTO;
import com.KmerQuiz.services.Interface.GainServiceInt;
import com.KmerQuiz.utils.TypeGain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gains")
@RequiredArgsConstructor
public class GainController {

    private final GainServiceInt gainService;

    // Créer un gain
    @PostMapping
    public ResponseEntity<GainDTO> createGain(@Valid @RequestBody GainDTO gainDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gainService.createGain(gainDTO));
    }

    // Afficher tous les gains
    @GetMapping
    public ResponseEntity<List<GainDTO>> findAll() {
        return ResponseEntity.ok(gainService.getAllGains());
    }

    // Gains accessibles avec un score donné : /eligible?score=15
    @GetMapping("/eligible")
    public ResponseEntity<List<GainDTO>> findEligible(@RequestParam double score) {
        return ResponseEntity.ok(gainService.getGainsEligibleForScore(score));
    }


    @GetMapping("/type/{typeGain}")
    public ResponseEntity<List<GainDTO>> findByType(@PathVariable TypeGain typeGain) {
        return ResponseEntity.ok(gainService.getGainsByType(typeGain));
    }


    @GetMapping("/{idGain}")
    public ResponseEntity<GainDTO> findById(@PathVariable Long idGain) {
        return ResponseEntity.ok(gainService.getGainById(idGain));
    }


    @PutMapping("/{idGain}")
    public ResponseEntity<GainDTO> updateGain(
            @PathVariable Long idGain,
            @Valid @RequestBody GainDTO gainDTO) {
        return ResponseEntity.ok(gainService.updateGain(idGain, gainDTO));
    }


    @DeleteMapping("/{idGain}")
    public ResponseEntity<Void> deleteGain(@PathVariable Long idGain) {
        gainService.deleteGain(idGain);
        return ResponseEntity.noContent().build();
    }
}
