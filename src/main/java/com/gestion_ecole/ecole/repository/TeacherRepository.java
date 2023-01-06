package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
	Optional<Teacher> findByEmail(String email);
	Optional<Teacher> findByTelephone(String telephone);

}
 