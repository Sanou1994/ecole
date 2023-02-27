package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IStudentService {	      
	         //GESTION STUDENT
	  public Reponse createOrUpdateStudent(StudentDtoRequest student);
	  
	 }
