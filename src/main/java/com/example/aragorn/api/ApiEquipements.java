package com.example.aragorn.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aragorn.model.DTO.EquipementDTO;
import com.example.aragorn.model.entity.Equipement;
import com.example.aragorn.model.service.EquipementService;

@RestController
@RequestMapping("/api/equipements")
public class ApiEquipements {
    @Autowired private EquipementService equipementService;

    @GetMapping
    public List<EquipementDTO> getAllEquipements() {
        List<Equipement> equipement = equipementService.getAllEquipements();
        List<EquipementDTO> equipementDTO = new ArrayList<>();
        equipementDTO = equipement.stream().map(EquipementDTO::new).toList();
        return equipementDTO;
    }
    
    @GetMapping("/{equipementId}")
    public EquipementDTO getEquipementById(@PathVariable Integer equipementId) {
        Equipement equipement = equipementService.gEquipementById(equipementId);
        EquipementDTO equipementDTO = new EquipementDTO(equipement);
        return equipementDTO;
    }
}
