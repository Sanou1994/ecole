package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

}
