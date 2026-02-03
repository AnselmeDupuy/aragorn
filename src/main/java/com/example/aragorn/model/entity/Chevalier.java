package com.example.aragorn.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Chevalier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    private List<String> roles = new ArrayList<>();

    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

    @ManyToMany
    @JoinTable(
        name = "chevalier_equipements",
        joinColumns = @JoinColumn(name = "chevalier_id"),
        inverseJoinColumns = @JoinColumn(
            name = "equipement_id",
            unique = true
        )
    )
    private Set<Equipement> equipements;
}
