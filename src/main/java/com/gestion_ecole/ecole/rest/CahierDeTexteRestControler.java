package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gestion_ecole.ecole.dto.request.CahierDeTexteDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.ICahierDeTexteService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class CahierDeTexteRestControler {
	@Autowired
	private ICahierDeTexteService cahierDeTexteService;
	
	@PostMapping(Utility.ADD_CAHIERDETEXTE)
	public Reponse addCahierDeTexte( @RequestBody CahierDeTexteDtoRequest cahierDeTexte){
		Reponse resultatCreation = cahierDeTexteService.createOrUpdateCahierDeTexte(cahierDeTexte);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_CAHIERDETEXTE_BY_ID)
	public Reponse getCahierDeTexteById(@PathVariable(value = "id") Long cahierDeTexteId){		
		Reponse	cahierDeTexteUpdate =cahierDeTexteService.getCahierDeTexteById(cahierDeTexteId);		
		return cahierDeTexteUpdate ;
    }
	@GetMapping(Utility.GET_ALL_CAHIERDETEXTE)
    public Reponse getAllCahierDeTextes() 
    {
    	Reponse list = cahierDeTexteService.listeCahierDeTexte();       
        return list;
    }
	@GetMapping(Utility.DELETE_CAHIERDETEXTE_BY_ID)
	public Reponse deleteCahierDeTexte(@PathVariable(value = "id") Long cahierDeTexteId){
		Reponse   resultat = cahierDeTexteService.deleteCahierDeTexte(cahierDeTexteId);		
			
		return resultat;
    }

}
