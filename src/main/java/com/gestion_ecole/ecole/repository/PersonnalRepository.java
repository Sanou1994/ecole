package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Personnal;

public interface PersonnalRepository extends JpaRepository<Personnal,Long>{
	Optional<Personnal> findByEmail(String email);
	Optional<Personnal>  findByTelephone(String telephone);

}
 