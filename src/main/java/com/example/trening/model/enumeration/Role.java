package com.example.trening.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{

    ROLE_CLIENT, ROLE_TRAINER;
    @Override
    public String getAuthority() {
        return name();
    }
}
