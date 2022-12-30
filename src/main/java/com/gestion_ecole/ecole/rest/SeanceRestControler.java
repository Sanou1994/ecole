package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.SeanceService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class SeanceRestControler {
	@Autowired
	SeanceService seanceService;
	
	@PostMapping(Utility.ADD_SEANCE)
	public Reponse addSeance( @RequestBody SeanceDtoRequest seance){
		Reponse resultatCreation = seanceService.createSeance(seance);
		return resultatCreation;
    }

	/*@PostMapping(Utility.UPDATE_USER)
	public Reponse getUpdateUser( @RequestBody StudentDtoRequest user){		
		Reponse	 userUpdate = accountService.createOrUpdateUser(user);
				
		return userUpdate;
    } */
	
	@GetMapping(Utility.GET_SEANCE_BY_ID)
	public Reponse getFiliereById(@PathVariable(value = "id") Long seanceId){		
		Reponse	seanceUpdate =seanceService.findById(seanceId);		
		return seanceUpdate ;
    }
	@GetMapping(Utility.DELETE_SEANCE_BY_ID)
	public Reponse deleteFiliere(@PathVariable(value = "id") Long seanceId){
				Reponse   resultat = seanceService.delete(seanceId);		
			
					return resultat;
    }


}
