package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IPersonnalService {	      
	         //GESTION Parent
	  public Reponse createOrUpdatePersonnal(PersonnalDtoRequest personnal);
	  
	 }
