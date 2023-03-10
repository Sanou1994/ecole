package com.gestion_ecole.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
	Optional<Student> findByEmail(String email);
	Optional<Student>  findByTelephone(String telephone);

}
 