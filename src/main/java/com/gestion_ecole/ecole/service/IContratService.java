package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.ContratDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IContratService {
	      
	         //GESTION Contrat
	  public Reponse createOrUpdateContrat(ContratDtoRequest contrat);
	  public Reponse getContratById(Long id);
	  public Reponse bloquerContrat(Long id); 
	  public Reponse ListeContrats(); 

	 
	 }
