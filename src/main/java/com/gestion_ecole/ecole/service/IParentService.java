package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IParentService {	      
	         //GESTION Parent
	  public Reponse createOrUpdateParent(ParentDtoRequest Parent);
	  
	 }
