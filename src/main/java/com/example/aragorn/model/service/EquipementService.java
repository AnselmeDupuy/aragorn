package com.example.aragorn.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.entity.Equipement;
import com.example.aragorn.model.repository.EquipementRepository;

@Service
public class EquipementService {
    @Autowired private EquipementRepository equipementRepository;

    public List<Equipement>  getAllEquipements() {
        return equipementRepository.findAll();
    }

    public Equipement gEquipementById(Integer id) {
        return equipementRepository.findById(id).orElse(new Equipement());
    }

    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public void deleteEquipement(Integer id) {
        equipementRepository.deleteById(id);
    }
}
