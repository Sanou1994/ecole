package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IClasseService {
	      
	         //GESTION Classe
	  public Reponse createOrUpdateClasse(ClasseDtoRequest classe);
	  public Reponse getClasseById(Long id);
	  public Reponse bloquerClasse(Long id); 
	  public Reponse ListeClasses(); 

	 
	 }
