package com.gestion_ecole.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
	Teacher findByEmail(String email);
	Teacher findByTelephone(String telephone);

}
 