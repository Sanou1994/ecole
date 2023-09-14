package com.app.ecole.rest;
import com.app.ecole.dto.request.BanqueDtoRequest;
import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.BanqueService;
import com.app.ecole.service.GroupeService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BanqueRestControler {
	@Autowired
	private BanqueService banqueService;

	@PostMapping(Utility.BANQUES)
	public Reponse getAddUser(@RequestBody BanqueDtoRequest banqueDtoRequest){
		Reponse resultatCreation = banqueService.create(banqueDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_BANQUE_BY_ID)
	public Reponse deleterById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =banqueService.disableById(groupeID);
		return userUpdate ;
	}
	@GetMapping(Utility.BANQUE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =banqueService.getById(groupeID);
		return userUpdate ;
    }



	@GetMapping(Utility.BANQUES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = banqueService.getAll();
		return resultat;
    }
	      
}
