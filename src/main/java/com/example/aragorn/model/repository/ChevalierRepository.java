package com.example.aragorn.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aragorn.model.entity.Chevalier;

public interface ChevalierRepository extends JpaRepository<Chevalier, Integer> {

    Optional<Chevalier> findByName(String name);
}
