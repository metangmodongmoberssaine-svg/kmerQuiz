package com.KmerQuiz.security;

import com.KmerQuiz.entities.User;
import com.KmerQuiz.utils.UserStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private String idUser;
    private String pseudoUser;
    private String passwordUser;
    private String roleUser;
    private UserStatus statusUser;
    private List<simpleGrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.idUser =user.getIdUser();
        this.pseudoUser = user.getPseudoUser();
        this.passwordUser = user.getPasswordUser();
        this.roleUser = user.getRoleUser().name();
        this.statusUser = user.getStatusUser();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }



}
