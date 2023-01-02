package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.EmploiDuTemps;

import antlr.collections.List;

public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {

	Optional<EmploiDuTemps> findByTitre(String titre);
}
