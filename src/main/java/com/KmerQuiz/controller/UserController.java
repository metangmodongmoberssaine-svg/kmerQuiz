package com.KmerQuiz.controller;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.services.Implementation.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Afficher tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // Créer un utilisateur
    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO savedUser = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable String idUser) {

        userService.deleteUser(idUser);

        return ResponseEntity.noContent().build();
    }

    // Modifier un utilisateur
    @PutMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String idUser,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updatedUser = userService.updateUser(idUser, userRequestDTO);

        return ResponseEntity.ok(updatedUser);
    }
}