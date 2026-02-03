package com.example.aragorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/armurerie")
    public String armurerie() {
        return "armurerie";
    }

    @GetMapping("/champ-de-bataille")
    public String champDeBataille() {
        return "champ-de-bataille";
    }

    @GetMapping("/taverne")
    public String taverne() {
        return "taverne";
    }
}
