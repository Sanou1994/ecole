package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.InscriptionDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IInscriptionService {
	//GESTION Inscription
	  public Reponse createOrUpdateInscription(InscriptionDtoRequest inscription);
	  public Reponse getInscriptionById(Long id);
	  public Reponse deleteInscription(Long id); 
	  public Reponse listeInscriptions(); 

}
