package com.KmerQuiz.utils;

import com.KmerQuiz.security.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum RoleUser {
    Admin(EnumSet.allOf(Permission.class)),
    Player(EnumSet.allOf(
            Permission.QUIZ_CREATE,
            Permission.QUIZ_READ,
            Permission.QUIZ_UPDATE,
            Permission.QUESTION_READ,
            Permission.RESPONSE_READ,
            Permission.CATEGORY_READ,
    ));
    private Set<Permission> permissions;

    RoleUser(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public List<SimpleGrantedAuthority> getAutorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        permissions.forEach(Permission permission -> authorities.add(new SimpleGrantedAuthority(permission.toString())));
        return authorities;
    }
}
