package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Personnal;

public interface PersonnalRepository extends JpaRepository<Personnal,Long>{
	Personnal findByEmail(String email);
	Personnal findByTelephone(String telephone);

}
 