package com.KmerQuiz.security;

import com.KmerQuiz.entities.User;
import com.KmerQuiz.utils.RoleUser;
import com.KmerQuiz.utils.UserStatus;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
        this.idUser =user.getIdUser();
        this.pseudoUser = user.getPseudoUser();
        this.passwordUser = user.getPasswordUser();
        this.roleUser = user.getRoleUser();
        this.statusUser = UserStatus.valueOf(user.getStatusUser());
        this.authorities = roleUser.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
