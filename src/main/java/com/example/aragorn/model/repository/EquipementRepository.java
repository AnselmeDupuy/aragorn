package com.example.aragorn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.aragorn.model.entity.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chevalier_equipements (chevalier_id, equipement_id) VALUES (:chevalierId, :equipementId)", nativeQuery = true)
    void addEquipementToChevalier(@Param("chevalierId") Integer chevalierId, @Param("equipementId") Integer equipementId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chevalier_equipements WHERE chevalier_id = :chevalierId AND equipement_id = :equipementId", nativeQuery = true)
    void removeEquipementFromChevalier(@Param("chevalierId") Integer chevalierId, @Param("equipementId") Integer equipementId);

    @Query(value = "SELECT COUNT(*) FROM chevalier_equipements WHERE chevalier_id = :chevalierId AND equipement_id = :equipementId", nativeQuery = true)
    Integer isEquipmentEquipped(@Param("chevalierId") Integer chevalierId, @Param("equipementId") Integer equipementId);
}
