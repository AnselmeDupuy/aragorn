package com.example.aragorn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aragorn.model.entity.Armurerie;

@Repository
public interface ArmurerieRepository extends JpaRepository<Armurerie, Integer> {

}
