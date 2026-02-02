package com.example.aragorn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aragorn.model.entity.Armurerie;
import com.example.aragorn.model.entity.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Integer> {

}
