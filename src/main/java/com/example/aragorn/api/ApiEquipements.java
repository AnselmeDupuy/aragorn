package com.example.aragorn.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/api/equipements")
public class ApiEquipements {

    @GetMapping
    public List<Equipement> getAllEquipements(Model model) {
        List<Equipement> equipements = EquipementService.selectAll();
        return equipements;
    }
    
    @GetMapping("/{equipement-id}")
    public Equipement getEquipementById(@PathVariable Integer equipement-id) {
        return EquipementService.selectEquipementById(equipement-id);
    }
}
