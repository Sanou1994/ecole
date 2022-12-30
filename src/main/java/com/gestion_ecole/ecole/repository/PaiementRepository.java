package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
