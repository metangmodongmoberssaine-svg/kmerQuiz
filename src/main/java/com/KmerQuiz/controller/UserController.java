package com.KmerQuiz.controller;

import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.services.Interface.UserServiceInt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // On injecte l'interface (et non la classe) : plus robuste avec @Transactional
    private final UserServiceInt userService;

    public UserController(UserServiceInt userService) {
        this.userService = userService;
    }

    // Afficher tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // Afficher un utilisateur par son id
    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String idUser) {
        return ResponseEntity.ok(userService.getUserById(idUser));
    }

    // Rechercher par pseudo (contient, insensible à la casse) : /search?token=abc
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> search(@RequestParam String token) {
        return ResponseEntity.ok(userService.searchUsers(token));
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
