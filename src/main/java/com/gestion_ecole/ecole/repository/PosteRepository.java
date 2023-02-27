package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Poste;

public interface PosteRepository extends JpaRepository<Poste,Long>{
	 Optional<Poste> findByNom(String nom);
	
}
 