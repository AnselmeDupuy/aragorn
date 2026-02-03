package com.example.aragorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.repository.ChevalierRepository;

@Controller
public class AuthController {

    @Autowired
    private ChevalierRepository chevalierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String homePath() {
        return "home";
    }

    // Endpoint temporaire pour initialiser les données
    @GetMapping("/init")
    @ResponseBody
    public String initializeData() {
        // Créer ou mettre à jour le chevalier "name"
        Chevalier chevalier = chevalierRepository.findByName("name")
                .orElse(new Chevalier());

        chevalier.setName("name");
        chevalier.setPassword(passwordEncoder.encode("password123")); // Mot de passe: password123

        chevalierRepository.save(chevalier);

        return "Chevalier 'name' initialized with password 'password123'";
    }
}
