package com.KmerQuiz.controller;

import com.KmerQuiz.dto.AuthResponseDTO;
import com.KmerQuiz.dto.LoginRequestDTO;
import com.KmerQuiz.dto.UserRequestDTO;
import com.KmerQuiz.dto.UserResponseDTO;
import com.KmerQuiz.security.JwtService;
import com.KmerQuiz.services.Interface.UserServiceInt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserServiceInt userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {

        Authentication authentification = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.pseudoUser(),
                        request.passwordUser()
                )
        );

        String token = jwtService.generateToken(authentification.getName());

        return ResponseEntity.ok(
                new AuthResponseDTO(token, "Bearer", null)
        );

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO user = userService.createUser(request);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.pseudoUser(),
                        request.passwordUser()
                )
        );

        String token = jwtService.generateToken(authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponseDTO(token, "Bearer", user));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {

        SecurityContextHolder.clearContext();

        return ResponseEntity.noContent().build();

    }
}