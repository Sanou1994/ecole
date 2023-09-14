package com.app.ecole.rest;
import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.InscriptionDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.AnneeService;
import com.app.ecole.service.InscriptionService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class InscriptionRestControler {
	@Autowired
	private InscriptionService inscriptionService;

	@PostMapping(Utility.INSCRIPTIONS)
	public Reponse getAddUser(@RequestBody InscriptionDtoRequest inscriptionDtoRequest){
		Reponse resultatCreation = inscriptionService.create(inscriptionDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.INSCRIPTIONS_SEARCH)
	public Reponse getUserId(@PathVariable(value = "el") String el){
		Reponse	userUpdate =inscriptionService.search(el);
		return userUpdate ;
	}
	@GetMapping(Utility.INSCRIPTIONS_SEARCH_ID)
	public Reponse getById(@PathVariable(value = "type") String type,@PathVariable(value = "id") UUID id){
		Reponse	userUpdate =inscriptionService.search(type,id);
		return userUpdate ;
	}
	
	@GetMapping(Utility.INSCRIPTION_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =inscriptionService.getById(groupeID);
		return userUpdate ;
    }
	@DeleteMapping(Utility.INSCRIPTION_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =inscriptionService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.INSCRIPTIONS)
	public Reponse getAllUsers()
	{
		Reponse   resultat = inscriptionService.getAll();
		return resultat;
    }
	      
}
