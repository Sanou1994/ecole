package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface ISceanceService {
	      
	         //GESTION Sceance
	  public Reponse createOrUpdateSceance(SeanceDtoRequest sceance);
	  public Reponse getSceanceById(Long id);
	  public Reponse bloquerSceance(Long id); 
	  public Reponse ListeSceances(); 

	 
	 }
