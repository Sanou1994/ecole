package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.AnneeScolaireDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IAnneScolaireService {
	      
	         //GESTION AnneScolaire
	  public Reponse createOrUpdateAnneScolaire(AnneeScolaireDtoRequest anneScolaire);
	  public Reponse getAnneScolaireById(Long id);
	  public Reponse bloquerAnneScolaire(Long id); 
	  public Reponse ListeAnneScolaires(Long id); 

	 
	 }
