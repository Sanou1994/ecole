package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.Ass_prinscription_studentDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IAssInscriptionStudentService {
	      
	         //GESTION DEPARTEMENT
	  public Reponse createOrUpdateAssInscriptionStudent(Ass_prinscription_studentDtoRequest assInscriptionStudent);
	  public Reponse getAssInscriptionStudentById(Long id);
	  public Reponse bloquerAssInscriptionStudent(Long id); 
	  public Reponse ListeAssInscriptionStudents(Long id); 

	 
	 }
