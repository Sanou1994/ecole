package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
	Student findByLogin(String login);
	Student findByEmail(String email);
}
 