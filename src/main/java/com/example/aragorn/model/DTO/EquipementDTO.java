package com.example.aragorn.model.DTO;

import com.example.aragorn.model.entity.Equipement;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EquipementDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    public EquipementDTO(Equipement equipement) {
        this.id = equipement.getId();
        this.name = equipement.getName();
        this.type = equipement.getType();
    }
}