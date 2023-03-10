package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IDepartementService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class DepartementRestControler {

	@Autowired
	private IDepartementService departementService;
	
	@PostMapping(Utility.ADD_DEPARTEMENT)
	public Reponse addDepartement( @RequestBody DepartementDtoRequest departement){
		Reponse resultatCreation = departementService.createOrUpdateDepartement(departement);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_DEPARTEMENT_BY_ID)
	public Reponse getDepartementById(@PathVariable(value = "id") Long departementId){		
		Reponse	userUpdate =departementService.getDepartementById(departementId);		
		return userUpdate ;
    }
	@GetMapping(Utility.GET_ALL_DEPARTEMENTS)
    public Reponse getAllDepartements(@PathVariable(value = "id") Long departementId) {
    	Reponse list = departementService.ListeDepartements(departementId);       
        return list;
    }
	@GetMapping(Utility.DELETE_DEPARTEMENT_BY_ID)
	public Reponse getDeleteDepartement(@PathVariable(value = "id") Long departementId){
				Reponse   resultat = departementService.bloquerDepartement(departementId);		
			
					return resultat;
    }
}
