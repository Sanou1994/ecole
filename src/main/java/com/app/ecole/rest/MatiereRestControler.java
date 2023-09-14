package com.app.ecole.rest;
import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.dto.request.MatiereDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.BanqueService;
import com.app.ecole.service.MatiereService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MatiereRestControler {
	@Autowired
	private MatiereService matiereService;

	@PostMapping(Utility.MATIERES)
	public Reponse getAddUser(@RequestBody MatiereDtoRequest matiereDtoRequest){
		Reponse resultatCreation = matiereService.create(matiereDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_MATIERES_BY_ID)
	public Reponse delete(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =matiereService.disableById(groupeID);
		return userUpdate ;
	}
	
	@GetMapping(Utility.MATIERE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =matiereService.getById(groupeID);
		return userUpdate ;
    }
	@DeleteMapping(Utility.MATIERE_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =matiereService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.MATIERES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = matiereService.getAll();
		return resultat;
    }
	      
}
