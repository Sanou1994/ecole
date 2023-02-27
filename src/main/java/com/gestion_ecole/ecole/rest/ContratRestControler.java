package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.ContratDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IContratService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class ContratRestControler {
	@Autowired
	private IContratService contratService;
	
	@PostMapping(Utility.ADD_CONTRAT)
	public Reponse addContrat( @RequestBody ContratDtoRequest contrat){
		Reponse resultatCreation = contratService.createOrUpdateContrat(contrat);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_CONTRAT_BY_ID)
	public Reponse getContratById(@PathVariable(value = "id") Long contratId){		
		Reponse	ContratUpdate =contratService.getContratById(contratId);		
		return ContratUpdate ;
    }
	@GetMapping(Utility.GET_ALL_CONTRAT)
    public Reponse getAllContrats() 
    {
    	Reponse list = contratService.ListeContrats();       
        return list;
    }
	@GetMapping(Utility.DELETE_CONTRAT_BY_ID)
	public Reponse deleteContrat(@PathVariable(value = "id") Long contratId){
		Reponse   resultat = contratService.bloquerContrat(contratId);		
			
		return resultat;
    }
}
