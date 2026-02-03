package com.example.aragorn.controller.chevalier;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.entity.Equipement;
import com.example.aragorn.model.service.ChevalierService;
import com.example.aragorn.model.service.EquipementService;

@Controller
@RequestMapping({"/armurerie", "/chevalier/armurerie"})
@Transactional
public class ArmurerieChevalierController {

    @Autowired
    private ChevalierService chevalierService;

    @Autowired
    private EquipementService equipementService;

    @GetMapping
    public String showArmurerie(Principal principal, Model model) {
        System.out.println("=== ARMURERIE PAGE ===");
        if (principal != null) {
            String chevalierName = principal.getName();
            System.out.println("Chevalier connecté: " + chevalierName);
            Chevalier chevalier = chevalierService.getChevalierByName(chevalierName);

            if (chevalier != null && chevalier.getId() > 0) {
                System.out.println("Chevalier trouvé avec l'ID: " + chevalier.getId());

                // Récupérer les IDs des équipements équipés via les ID du chevalier
                // plutôt que d'utiliser la collection lazy
                java.util.List<Equipement> equipementsEquipes = chevalierService.getEquipementsByChevalierId(chevalier.getId());
                model.addAttribute("equipementsEquipes", equipementsEquipes);
                System.out.println("Equipements équipés: " + equipementsEquipes.size());

                // Récupérer tous les équipements disponibles
                java.util.List<Equipement> tousEquipementsList = equipementService.getAllEquipements();
                System.out.println("Total d'équipements en BDD: " + tousEquipementsList.size());

                // Créer une liste d'équipements disponibles (ceux non équipés)
                java.util.List<Equipement> equipementsDisponiblesList = new java.util.ArrayList<>();
                java.util.Set<Integer> equipementsEquipesIds = new java.util.HashSet<>();
                for (Equipement eq : equipementsEquipes) {
                    equipementsEquipesIds.add(eq.getId());
                }
                for (Equipement eq : tousEquipementsList) {
                    if (!equipementsEquipesIds.contains(eq.getId())) {
                        equipementsDisponiblesList.add(eq);
                    }
                }
                model.addAttribute("equipementsDisponibles", equipementsDisponiblesList);
                System.out.println("Equipements disponibles: " + equipementsDisponiblesList.size());

                model.addAttribute("chevalier", chevalier);
            } else {
                System.out.println("Chevalier not found or invalid ID");
            }
        } else {
            System.out.println("Principal is null");
        }
        return "armurerie";
    }

    @PostMapping("/equiper/{equipementId}")
    public String equiper(@PathVariable Integer equipementId, Principal principal) {
        if (principal != null) {
            String chevalierName = principal.getName();
            Chevalier chevalier = chevalierService.getChevalierByName(chevalierName);

            if (chevalier != null && chevalier.getId() > 0) {
                equipementService.addEquipementToChevalier(chevalier.getId(), equipementId);
            }
        }
        return "redirect:/chevalier/armurerie";
    }

    @PostMapping("/desequiper/{equipementId}")
    public String desequiper(@PathVariable Integer equipementId, Principal principal) {
        if (principal != null) {
            String chevalierName = principal.getName();
            Chevalier chevalier = chevalierService.getChevalierByName(chevalierName);

            if (chevalier != null && chevalier.getId() > 0) {
                equipementService.removeEquipementFromChevalier(chevalier.getId(), equipementId);
            }
        }
        return "redirect:/chevalier/armurerie";
    }
}
