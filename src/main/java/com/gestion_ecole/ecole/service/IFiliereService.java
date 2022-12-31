package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IFiliereService {
	      
	         //GESTION Filiere
	  public Reponse createOrUpdateFiliere(FiliereDtoRequest filiere);
	  public Reponse getFiliereById(Long id);
	  public Reponse bloquerFiliere(Long id); 
	  public Reponse ListeFilieres(); 

	 
	 }
