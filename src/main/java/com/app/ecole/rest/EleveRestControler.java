package com.app.ecole.rest;
import com.app.ecole.dto.request.EleveDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.EleveService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
public class EleveRestControler {
	@Autowired
	private EleveService eleveService;

	@PostMapping(Utility.ELEVES)
	public Reponse getAddUser(@RequestBody EleveDtoRequest eleveDtoRequest){
		Reponse resultatCreation = eleveService.create(eleveDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.ELEVES_SOLDE)
	public Reponse soldeEleve(@PathVariable(value = "id") UUID id){
		Reponse	userUpdate =eleveService.solde(id);
		return userUpdate ;
	}
	@GetMapping(Utility.ELEVES_STATISTIQUE)
	public Reponse soldeEleve(
			@RequestParam(value="annee",required = false)  UUID annee,
			@RequestParam(value="dateDebut",required = false)  Long dateDebut,
			@RequestParam(value="dateFin" ,required = false)  Long dateFin

	){
		Reponse	userUpdate =eleveService.statEleve(annee, dateDebut, dateFin);
		return userUpdate ;

	}
	@GetMapping(Utility.CHANGE_STATUS_ELEVE_BY_ID)
	public Reponse changeEleveStatus(@PathVariable(value = "id") UUID id){
		Reponse	userUpdate =eleveService.disableById(id);
		return userUpdate ;

	}
	@GetMapping(Utility.ELEVES_BY_CLASSE)
	public Reponse searchEleve(@PathVariable(value = "id") UUID id){
		Reponse	userUpdate =eleveService.getElevesByClasse(id);
		return userUpdate ;
	}
	@GetMapping(Utility.ELEVES_DETAILS)
	public Reponse getDetailById(@PathVariable(value = "id") UUID groupeID)
	{
		Reponse	userUpdate =eleveService.getDetails(groupeID);
		return userUpdate ;
	}
	@GetMapping(Utility.ELEVES_SEARCH)
	public Reponse search(@PathVariable(value = "el") String el){
		Reponse	userUpdate =eleveService.search(el);
		return userUpdate ;
	}
	@GetMapping(Utility.ELEVE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =eleveService.getById(groupeID);
		return userUpdate ;
    }
	@DeleteMapping(Utility.ELEVE_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =eleveService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.ELEVES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = eleveService.getAll();
		return resultat;
    }
	      
}
