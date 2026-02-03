package com.example.aragorn.controller.chevalier;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.service.ChevalierService;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {

    @Autowired
    private ChevalierService chevalierService;

    @GetMapping("/home")
    public String home(Model model, Principal principal, HttpSession session) {
        System.out.println("=== HOME PAGE ACCESSED ===");
        if (principal != null) {
            System.out.println("User authenticated: " + principal.getName());
            // Get the authenticated chevalier's name
            String chevalierName = principal.getName();
            Chevalier chevalier = chevalierService.getChevalierByName(chevalierName);
            if (chevalier != null && chevalier.getId() > 0) {
                System.out.println("Chevalier loaded: " + chevalier.getName());
                
                // Store in session
                session.setAttribute("chevalierName", chevalier.getName());
                session.setAttribute("chevalierRoles", chevalier.getRoles());
                session.setAttribute("chevalier", chevalier);
                
                model.addAttribute("chevalier", chevalier);
            }
        } else {
            System.out.println("No authenticated user");
        }
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("=== LOGIN PAGE ACCESSED ===");
        // Check if already authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            System.out.println("Already authenticated, redirecting to home");
            return "redirect:/";
        }
        return "login";
    }
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("chevalier", new Chevalier());

        return "register_form";
    }

    @PostMapping("/register")
    public String postMethodName(Model model, @ModelAttribute Chevalier chevalier) {
        model.addAttribute("chevalier", chevalier);
        chevalierService.registerChevalier(chevalier);
        
        return "home";
    }
    
    
}