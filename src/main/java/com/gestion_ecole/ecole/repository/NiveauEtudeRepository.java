package com.gestion_ecole.ecole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Niveau_etude;

public interface NiveauEtudeRepository extends JpaRepository<Niveau_etude,Long>{
	Optional<Niveau_etude> findByNom(String nom);
	List<Niveau_etude> findByStructureID(Long id);
}
 