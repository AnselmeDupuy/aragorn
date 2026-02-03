package com.example.aragorn.model.DTO;

import com.example.aragorn.model.entity.Armurerie;

import lombok.Data;

@Data
public class ArmurerieDTO {
    
    private int id;

    public ArmurerieDTO(Armurerie armurerie) {
        this.id = armurerie.getId();
    }
}
