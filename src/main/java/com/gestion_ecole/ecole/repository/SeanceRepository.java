package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Sceance;

public interface SeanceRepository extends JpaRepository<Sceance, Long> {
	Optional<Sceance> findByType(String type);
}
