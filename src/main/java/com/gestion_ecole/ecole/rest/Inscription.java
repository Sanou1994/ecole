package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.InscriptionDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IInscriptionService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class Inscription {
	@Autowired
	private IInscriptionService inscriptionService;
	
	@PostMapping(Utility.ADD_INSCRIPTION)
	public Reponse addInscription( @RequestBody InscriptionDtoRequest inscriptionDtoRequest){
		Reponse resultatCreation = inscriptionService.createOrUpdateInscription(inscriptionDtoRequest);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_INSCRIPTION_BY_ID)
	public Reponse getInscriptionById(@PathVariable(value = "id") Long inscriptionId){		
		Reponse	inscriptionUpdate =inscriptionService.getInscriptionById(inscriptionId);		
		return inscriptionUpdate ;
    }
	@GetMapping(Utility.GET_ALL_INSCRIPTION)
    public Reponse getAllInscription() 
    {
    	Reponse list = inscriptionService.listeInscriptions();       
        return list;
    }
	@GetMapping(Utility.DELETE_INSCRIPTION_BY_ID)
	public Reponse deleteInscription(@PathVariable(value = "id") Long inscriptionId){
		Reponse   resultat = inscriptionService.deleteInscription(inscriptionId);		
			
		return resultat;
    }


}
