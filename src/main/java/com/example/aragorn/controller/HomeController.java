package com.example.aragorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.aragorn.model.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, 
                       @RequestParam(required = false) String registered,
                       @RequestParam(required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        if (registered != null) {
            model.addAttribute("message", "Registration successful! Please login.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Registration failed. Please try again.");
        }
        return "register";
    }
    
    @PostMapping("/register")
    public String registerPost(@RequestParam String username, 
                              @RequestParam String password, 
                              @RequestParam String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return "redirect:/register?error";
        }
        
        try {
            userService.registerUser(username, password);
            return "redirect:/login?registered";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }
}
