package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Code;

public interface CodeRepository extends JpaRepository<Code,Long>{
	 Optional<Code> findByCode(int code);

}
 