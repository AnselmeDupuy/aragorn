package com.example.aragorn.model.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ConnectedUser implements UserDetails {
    private final Chevalier chevalier;

    public ConnectedUser(Chevalier chevalier) {
        this.chevalier = chevalier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return chevalier.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());
    }

    @Override public String getPassword() { return chevalier.getPassword(); }
    @Override public String getUsername() { return chevalier.getName(); }

}
