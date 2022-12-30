package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Classe;

public interface ClasseRepository extends JpaRepository<Classe,Long>{
	 Optional<Classe> findByNom(String nom);
	
}
 