package com.example.aragorn.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.entity.ConnectedUser;
import com.example.aragorn.model.repository.ChevalierRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired ChevalierRepository chevalierRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chevalier chevalier = chevalierRepository.findByName(username).orElseThrow(
            () -> new UsernameNotFoundException("Aucun utilisateur trouvé avec le pseudo " + username)
        );
        return new ConnectedUser(chevalier);
    }
}