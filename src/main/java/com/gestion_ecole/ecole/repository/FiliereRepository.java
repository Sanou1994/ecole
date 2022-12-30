package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Filiere;

public interface FiliereRepository extends JpaRepository<Filiere,Long>{
	Filiere findByTitre (String titre);
	
}
 