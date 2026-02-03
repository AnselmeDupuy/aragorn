package com.example.aragorn.model.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ConnectedUser implements UserDetails {

    private final Chevalier chevalier;

    public ConnectedUser(Chevalier chevalier) {
        this.chevalier = chevalier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return chevalier.getAuthorities();
    }

    @Override
    public String getPassword() {
        return chevalier.getPassword();
    }

    @Override
    public String getUsername() {
        return chevalier.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
