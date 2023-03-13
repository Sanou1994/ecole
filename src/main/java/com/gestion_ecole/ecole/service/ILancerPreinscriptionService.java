package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.LancerPreinscriptionDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface ILancerPreinscriptionService {
	      
	         //GESTION DEPARTEMENT
	  public Reponse createOrUpdateLancerPreinscription(LancerPreinscriptionDtoRequest lancerPreinscription);
	  public Reponse getLancerPreinscriptionById(Long id);
	  public Reponse bloquerLancerPreinscription(Long id); 
	  public Reponse ListeLancerPreinscriptions(Long id); 

	 
	 }
