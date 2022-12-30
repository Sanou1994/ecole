package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.ClasseService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class ClasseRestControler {
	@Autowired
	ClasseService classeService;
	
	@PostMapping(Utility.ADD_CLASSE)
	public Reponse addClasse( @RequestBody ClasseDtoRequest classe){
		Reponse resultatCreation = classeService.createClasse(classe);
		return resultatCreation;
    }

	/*@PostMapping(Utility.UPDATE_USER)
	public Reponse getUpdateUser( @RequestBody StudentDtoRequest user){		
		Reponse	 userUpdate = accountService.createOrUpdateUser(user);
				
		return userUpdate;
    } */
	
	@GetMapping(Utility.GET_CLASSE_BY_ID)
	public Reponse getClasseById(@PathVariable(value = "id") Long classeId){		
		Reponse	classeUpdate =classeService.findById(classeId);		
		return classeUpdate ;
    }
	@GetMapping(Utility.DELETE_CLASSE_BY_ID)
	public Reponse deleteClasse(@PathVariable(value = "id") Long classeId){
				Reponse   resultat = classeService.delete(classeId);		
			
					return resultat;
    }
}
