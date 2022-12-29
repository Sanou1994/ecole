package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Personnal;

public interface PersonnalRepository extends JpaRepository<Personnal,Long>{
	Personnal findByLogin(String login);
	Personnal findByEmail(String email);
}
 