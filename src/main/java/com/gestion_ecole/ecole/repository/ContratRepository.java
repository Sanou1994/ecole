package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Contrat;

public interface ContratRepository extends JpaRepository<Contrat,Long>{
	 Optional<Contrat> findByType(String type);
	
}
 