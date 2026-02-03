package com.example.aragorn.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.aragorn.model.entity.Chevalier;

public interface ChevalierRepository extends JpaRepository<Chevalier, Integer> {
    public Optional<Chevalier> findByName(String name);
    public List<Chevalier> findByNameContaining(String name);
    @Query("SELECT DISTINCT roles FROM Chevalier c")
    List<String> findDistinctRoles();

    @Query("SELECT DISTINCT name FROM Chevalier u")
    Chevalier findDistinctUserByName();

        


}
