package com.example.aragorn.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/chevaliers")
public class ApiChevalier {
 
    @GetMapping
    public List<Chevalier> getAllChevaliers() {
        return ChevalierService.selectAll();
    }

    @GetMapping("/{chevalier-id}")
    public Chevalier getChevalierById(@PathVariable Integer chevalier-id) {
        return chevalierService.selectChevalierById(chevalier-id);
    }
}
