package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface ITeacherService {	      
	         //GESTION Parent
	  public Reponse createOrUpdateTeacher(TeacherDtoRequest teacher);
	  
	 }
