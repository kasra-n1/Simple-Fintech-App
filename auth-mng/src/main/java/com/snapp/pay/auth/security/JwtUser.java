package com.snapp.pay.auth.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class JwtUser extends User {

    private final Long userId;

    public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
