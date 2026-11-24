package com.KmerQuiz.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserPrincipalService userPrincipalService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Récupérer le header Authorization
        String authHeader = request.getHeader("Authorization");

        // Vérifier la présence du Bearer Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Extraire le token
        String token = authHeader.substring(7);

        try {

            // Vérifier le token
            if (jwtService.validateToken(token)) {

                // Récupérer le username
                String username = jwtService.getUsernameFromToken(token);

                // Vérifier si aucune authentification n'existe
                if (username != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Charger l'utilisateur
                    UserDetails userDetails =
                            userPrincipalService.loadUserByUsername(username);

                    // Créer l'authentification
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Enregistrer l'authentification
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }

        } catch (Exception e) {

            SecurityContextHolder.clearContext();
        }

        // Continuer la chaîne des filtres
        filterChain.doFilter(request, response);
    }
}