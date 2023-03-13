package com.gestion_ecole.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_ecole.ecole.entities.Ass_prinscription_student;

public interface AssInscriptionStudentRepository extends JpaRepository<Ass_prinscription_student, Long> {
	List<Ass_prinscription_student> findByStructureID(long id);
}
