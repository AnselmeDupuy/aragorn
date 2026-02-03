package com.example.aragorn.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.aragorn.model.entity.Chevalier;
import com.example.aragorn.model.entity.Equipement;

public interface ChevalierRepository extends JpaRepository<Chevalier, Integer> {

    Optional<Chevalier> findByName(String name);

    List<Chevalier> findByNameContaining(String name);

    @Query("SELECT e FROM Equipement e JOIN e.chevaliers c WHERE c.id = :chevalierId")
    List<Equipement> findEquipementsByChevalierId(@Param("chevalierId") Integer chevalierId);
}
