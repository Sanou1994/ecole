package com.gestion_ecole.ecole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement,Long>{
	Optional<Departement> findByNom(String nom);
	List<Departement> findByStructureID(Long id);
}
 