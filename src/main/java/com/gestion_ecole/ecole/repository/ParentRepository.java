package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent,Long>{
	Parent findByTelephone(String telephone);

	Parent findByEmail(String email);
}
 