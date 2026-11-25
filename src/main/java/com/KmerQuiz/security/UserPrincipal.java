package com.KmerQuiz.security;

import com.KmerQuiz.entities.User;
import com.KmerQuiz.utils.RoleUser;
import com.KmerQuiz.utils.UserStatus;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {
    private final String idUser;
    private final String pseudoUser;
    private final String passwordUser;
    private final RoleUser roleUser;
    private final UserStatus statusUser;
    private final List<SimpleGrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.idUser = user.getIdUser();
        this.pseudoUser = user.getPseudoUser();
        this.passwordUser = user.getPasswordUser();
        this.roleUser = user.getRoleUser();
        this.statusUser = parseStatus(user.getStatusUser());

        // Permissions du role (QUIZ_READ, ...) + le role lui-meme (ROLE_ADMIN, ROLE_PLAYER)
        List<SimpleGrantedAuthority> list = new ArrayList<>(roleUser.getAuthorities());
        list.add(new SimpleGrantedAuthority("ROLE_" + roleUser.name().toUpperCase()));
        this.authorities = list;
    }

    // Convertit le texte de la base en enum, sans planter si la valeur est inconnue
    private static UserStatus parseStatus(String value) {
        if (value == null || value.isBlank()) {
            return UserStatus.INACTIVE;
        }
        try {
            return UserStatus.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserStatus.INACTIVE;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    // IMPORTANT : doit renvoyer le pseudo (et non "")
    @Override
    public String getUsername() {
        return pseudoUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Compte verrouille si BLOCKED ou SUSPENDED
    @Override
    public boolean isAccountNonLocked() {
        return statusUser != UserStatus.BLOCKED && statusUser != UserStatus.SUSPENDED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Seuls les comptes ACTIVE peuvent se connecter
    @Override
    public boolean isEnabled() {
        return statusUser == UserStatus.ACTIVE;
    }
}