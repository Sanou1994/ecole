package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IDepartementService {
	      
	         //GESTION DEPARTEMENT
	  public Reponse createOrUpdateDepartement(DepartementDtoRequest departement);
	  public Reponse getDepartementById(Long id);
	  public Reponse bloquerDepartement(Long id); 
	  public Reponse ListeDepartements(); 

	 
	 }
