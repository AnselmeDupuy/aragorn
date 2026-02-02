package com.example.aragorn.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.repository.ChevalierRepository;

@Service
public class ChevalierService {
    @Autowired private ChevalierRepository chevalierRepository;

    public Chevalier getChevalierById(Integer id) {
        return chevalierRepository.findById(id).orElse(new Chevalier());
    }

    public List<Chevalier> getAllChevaliers() {
        return chevalierRepository.findAll();
    }

    public Chevalier saveChevalier(Chevalier chevalier) {
        return chevalierRepository.save(chevalier);
    }

    public void deleteChevalier(Integer id) {
        chevalierRepository.deleteById(id);
    }
}
