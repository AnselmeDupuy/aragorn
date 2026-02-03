package com.example.aragorn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.repository.ChevalierRepository;

@Service
public class ChevalierUserDetailsService implements UserDetailsService {

    @Autowired
    private ChevalierRepository chevalierRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username correspond au champ 'name' du Chevalier
        return chevalierRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Chevalier not found: " + username));
    }
}
