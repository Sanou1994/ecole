package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IEmploiDuTempsService {
	//GESTION EmploiDuTemps
	  public Reponse createOrUpdateEmploiDuTemps(EmploiDuTempsDtoRequest emploiDuTempsDtoRequest);
	  public Reponse getEmploiDuTempsById(Long id);
	  public Reponse deleteEmploiDuTemps(Long id); 
	  public Reponse listeEmploiDuTemps(); 

}
