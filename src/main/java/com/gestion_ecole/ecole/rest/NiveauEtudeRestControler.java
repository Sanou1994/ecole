package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.NiveauEtudeDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.INiveauEtudeService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class NiveauEtudeRestControler {

	@Autowired
	private INiveauEtudeService niveauEtudeService;
	
	@PostMapping(Utility.ADD_NIVEAU_ETUDE)
	public Reponse addNiveauEtude( @RequestBody NiveauEtudeDtoRequest niveauEtude){
		Reponse resultatCreation = niveauEtudeService.createOrUpdateNiveauEtude(niveauEtude);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_NIVEAU_ETUDE_BY_ID)
	public Reponse getNiveauEtudeById(@PathVariable(value = "id") Long niveauEtudeId){		
		Reponse	userUpdate =niveauEtudeService.getNiveauEtudeById(niveauEtudeId);		
		return userUpdate ;
    }
	@GetMapping(Utility.GET_ALL_NIVEAU_ETUDES)
    public Reponse getAllNiveauEtudes(@PathVariable(value = "id") Long niveauEtudeId) {
    	Reponse list = niveauEtudeService.ListeNiveauEtude(niveauEtudeId);       
        return list;
    }
	@GetMapping(Utility.DELETE_NIVEAU_ETUDE_BY_ID)
	public Reponse getDeleteNiveauEtude(@PathVariable(value = "id") Long niveauEtudeId){
				Reponse   resultat = niveauEtudeService.bloquerNiveauEtude(niveauEtudeId);		
			
					return resultat;
    }
}
