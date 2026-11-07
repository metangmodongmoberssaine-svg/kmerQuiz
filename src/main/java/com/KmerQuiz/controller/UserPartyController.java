package com.KmerQuiz.controller;

import com.KmerQuiz.dto.UserPartyDTO;
import com.KmerQuiz.services.Interface.UserPartyServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parties")
@RequiredArgsConstructor
public class UserPartyController {

    private final UserPartyServiceInt userPartyService;


    @PostMapping("/users/idUser/codeParty")
    public ResponseEntity<UserPartyDTO> joinParty(
            @PathVariable String idUser,
            @PathVariable Long codeParty) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPartyService.joinParty(idUser, codeParty));
    }

    @PatchMapping("/users/idUser/codeParty")
    public ResponseEntity<UserPartyDTO> updateScore(
            @PathVariable String idUser,
            @PathVariable Long codeParty,
            @RequestParam double score) {
        return ResponseEntity.ok(userPartyService.updateScore(idUser, codeParty, score));
    }


    @GetMapping("/parties/idUser/codeParty")
    public ResponseEntity<List<UserPartyDTO>> getParticipants(@PathVariable Long codeParty) {
        return ResponseEntity.ok(userPartyService.getPartyParticipants(codeParty));
    }

    @GetMapping("/parties/codePartyleaderboard")
    public ResponseEntity<List<UserPartyDTO>> getLeaderboard(@PathVariable Long codeParty) {
        return ResponseEntity.ok(userPartyService.getPartyLeaderboard(codeParty));
    }

    @GetMapping("/users/idUser/parties")
    public ResponseEntity<List<UserPartyDTO>> getUserParties(@PathVariable String idUser) {
        return ResponseEntity.ok(userPartyService.getUserParties(idUser));
    }

    @DeleteMapping("/users/idUser/codeParty")
    public ResponseEntity<Void> leaveParty(
            @PathVariable String idUser,
            @PathVariable Long codeParty) {
        userPartyService.leaveParty(idUser, codeParty);
        return ResponseEntity.noContent().build();
    }
}
