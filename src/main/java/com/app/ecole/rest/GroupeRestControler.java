package com.app.ecole.rest;
import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.request.Login;
import com.app.ecole.dto.request.UserDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.GroupeService;
import com.app.ecole.service.IAccountService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GroupeRestControler {
	@Autowired
	private GroupeService groupeService;

	@PostMapping(Utility.GROUPES)
	public Reponse getAddUser(@RequestBody GroupeDtoRequest groupeDtoRequest){
		Reponse resultatCreation = groupeService.create(groupeDtoRequest);
		return resultatCreation;
    }

	@GetMapping(Utility.CHANGE_STATUS_GROUPES_BY_ID)
	public Reponse deleteById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =groupeService.disableById(groupeID);
		return userUpdate ;
	}
	@GetMapping(Utility.GROUPE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =groupeService.getById(groupeID);
		return userUpdate ;
    }
	@DeleteMapping(Utility.GROUPE_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =groupeService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.GROUPES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = groupeService.getAll();
		return resultat;
    }
	      
}
