package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IFiliereService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class FiliereRestControler {
	@Autowired
	private IFiliereService filiereService;
	
	@PostMapping(Utility.ADD_FILIERE)
	public Reponse addFiliere( @RequestBody FiliereDtoRequest filiere){
		Reponse resultatCreation = filiereService.createOrUpdateFiliere(filiere);
		return resultatCreation;
    }

	
	
	@GetMapping(Utility.GET_FILIERE_BY_ID)
	public Reponse getFiliereById(@PathVariable(value = "id") Long filiereId){		
		Reponse	filiereUpdate =filiereService.getFiliereById(filiereId);		
		return filiereUpdate ;
    }
	@GetMapping(Utility.GET_ALL_FILIERE)
    public Reponse getAllFilieres(@PathVariable(value = "id") Long idStructure) 
    {
    	Reponse list = filiereService.ListeFilieres(idStructure);       
        return list;
    }
	@GetMapping(Utility.DELETE_FILIERE_BY_ID)
	public Reponse deleteFiliere(@PathVariable(value = "id") Long filiereId){
				Reponse   resultat = filiereService.bloquerFiliere(filiereId);	
			
					return resultat;
    }


}
