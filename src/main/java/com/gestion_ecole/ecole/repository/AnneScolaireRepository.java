package com.gestion_ecole.ecole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.AnneeScolaire;

public interface AnneScolaireRepository extends JpaRepository<AnneeScolaire,Long>{
	Optional<AnneeScolaire> findByLibelle(String nom);
	List<AnneeScolaire> findByStructureID(long id);
}
 