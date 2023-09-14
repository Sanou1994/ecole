package com.app.ecole.rest;
import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.AnneeService;
import com.app.ecole.service.BanqueService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AnneeRestControler {
	@Autowired
	private AnneeService anneeService;

	@PostMapping(Utility.ANNEES)
	public Reponse getAddUser(@RequestBody AnneeDtoRequest anneeDtoRequest){
		Reponse resultatCreation = anneeService.create(anneeDtoRequest);
		return resultatCreation;
    }
	
	@GetMapping(Utility.ANNEE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =anneeService.getById(groupeID);
		return userUpdate ;
    }
	@GetMapping(Utility.CHANGE_STATUS_ANNEE_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =anneeService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.ANNEES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = anneeService.getAll();
		return resultat;
    }
	      
}
