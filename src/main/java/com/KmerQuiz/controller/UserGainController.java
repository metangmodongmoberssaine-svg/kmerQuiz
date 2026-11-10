package com.KmerQuiz.controller;

import com.KmerQuiz.dto.UserGame;
import com.KmerQuiz.services.Interface.UserGainServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-gains")
@RequiredArgsConstructor
public class UserGainController {

    private final UserGainServiceInt userGainService;

    // Attribuer un gain à un utilisateur
    @PostMapping("/users/{idUser}/gains/{idGain}")
    public ResponseEntity<UserGame> awardGain(
            @PathVariable String idUser,
            @PathVariable Long idGain) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userGainService.awardGainToUser(idUser, idGain));
    }

    // Gains d'un utilisateur
    @GetMapping("/users/{idUser}/gains")
    public ResponseEntity<List<UserGame>> getGainsByUser(@PathVariable String idUser) {
        return ResponseEntity.ok(userGainService.getGainsByUser(idUser));
    }

    // Utilisateurs ayant reçu un gain
    @GetMapping("/gains/{idGain}/users")
    public ResponseEntity<List<UserGame>> getUsersByGain(@PathVariable Long idGain) {
        return ResponseEntity.ok(userGainService.getUsersByGain(idGain));
    }

    // Retirer un gain à un utilisateur
    @DeleteMapping("/users/{idUser}/gains/{idGain}")
    public ResponseEntity<Void> revokeGain(
            @PathVariable String idUser,
            @PathVariable Long idGain) {
        userGainService.revokeGainFromUser(idUser, idGain);
        return ResponseEntity.noContent().build();
    }
}
