package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.NiveauEtudeDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface INiveauEtudeService {
	      
	         //GESTION DEPARTEMENT
	  public Reponse createOrUpdateNiveauEtude(NiveauEtudeDtoRequest niveauEtude);
	  public Reponse getNiveauEtudeById(Long id);
	  public Reponse bloquerNiveauEtude(Long id); 
	  public Reponse ListeNiveauEtude(Long id); 

	 
	 }
