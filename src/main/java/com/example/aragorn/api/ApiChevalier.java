package com.example.aragorn.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aragorn.model.DTO.ChevalierDTO;
import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.service.ChevalierService;


@RestController
@RequestMapping("/api/chevaliers")
public class ApiChevalier {
    @Autowired private ChevalierService chevalierService;
 
    @GetMapping
    public List<ChevalierDTO> getAllChevaliers() {
        List<Chevalier> chevalier = chevalierService.getAllChevaliers();
        List<ChevalierDTO> chevalierDTO = new ArrayList<>();
        chevalierDTO = chevalier.stream().map(ChevalierDTO::new).toList();
        return chevalierDTO;
    }

    @GetMapping("/{chevalierId}")
    public ChevalierDTO getChevalierById(@PathVariable Integer chevalierId) {
        Chevalier chevalier = chevalierService.getChevalierById(chevalierId);
        ChevalierDTO chevalierDTO = new ChevalierDTO(chevalier);
        return chevalierDTO;
    
    }
}
