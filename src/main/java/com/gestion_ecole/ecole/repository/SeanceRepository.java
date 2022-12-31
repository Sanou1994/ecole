package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Seance;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
	Optional<Seance> findByType(String type);
}
