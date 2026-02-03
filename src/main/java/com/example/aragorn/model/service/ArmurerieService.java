package com.example.aragorn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aragorn.model.entity.Armurerie;
import com.example.aragorn.model.repository.ArmurerieRepository;

@Service
public class ArmurerieService {
    @Autowired private ArmurerieRepository armurerieRepository;

    public Armurerie getChevalierById(Integer id) {
        return armurerieRepository.findById(id).orElse(new Armurerie());
    }

    public Armurerie saveArmurerie(Armurerie armurerie) {
        return armurerieRepository.save(armurerie);
    }

    public void deleteArmurerie(Integer id) {
        armurerieRepository.deleteById(id);
    }

}
