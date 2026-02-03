package com.example.aragorn.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.repository.ChevalierRepository;

@Service
public class ChevalierService {

    @Autowired private ChevalierRepository chevalierRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public Chevalier getChevalierById(Integer id) {
        return chevalierRepository.findById(id).orElse(new Chevalier());
    }

    public Chevalier getChevalierByName(String name) {
        return chevalierRepository.findByName(name).orElse(new Chevalier());
    }

    public List<Chevalier> getAllChevaliers() {
        return chevalierRepository.findAll();
    }

    public Chevalier saveChevalier(Chevalier chevalier) {
        return chevalierRepository.save(chevalier);
    }

    public void registerChevalier(Chevalier chevalier) {
        chevalier.setPassword(passwordEncoder.encode(chevalier.getPassword()));
        chevalier.addRole("ROLE_CHEVALIER");
        chevalierRepository.save(chevalier);
    }

    public void deleteChevalier(Integer id) {
        chevalierRepository.deleteById(id);
    }
}
