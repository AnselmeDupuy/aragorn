package com.example.aragorn.model.DTO;

import com.example.aragorn.model.entity.Chevalier;

import lombok.Data;


@Data
public class ChevalierDTO {
   
    private int id;
    private String name;

    public ChevalierDTO(Chevalier chevalier) {
        this.id = chevalier.getId();
        this.name = chevalier.getName();
    }
}
