package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent,Long>{
	Optional<Parent> findByTelephone(String telephone);

	Optional<Parent> findByEmail(String email);
}
 