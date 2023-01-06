package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.CahierDeTexteDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface ICahierDeTexteService {
	//GESTION CahierDeTexte
	  public Reponse createOrUpdateCahierDeTexte(CahierDeTexteDtoRequest cahierDeTexteDtoRequest);
	  public Reponse getCahierDeTexteById(Long id);
	  public Reponse deleteCahierDeTexte(Long id); 
	  public Reponse listeCahierDeTexte(); 


}
