package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.PosteDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IPosteService {
	      
	         //GESTION Poste
	  public Reponse createOrUpdatePoste(PosteDtoRequest poste);
	  public Reponse getPosteById(Long id);
	  public Reponse bloquerPoste(Long id); 
	  public Reponse ListePostes(); 

	 
	 }
