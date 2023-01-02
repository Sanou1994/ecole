package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IAbsenceService {
	//GESTION Absence
	  public Reponse createOrUpdateAbsence(AbsenceDtoRequest absence);
	  public Reponse getAbsenceById(Long id);
	  public Reponse deleteAbsence(Long id); 
	  public Reponse listeAbsences(); 


}
