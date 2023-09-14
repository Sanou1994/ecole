package com.app.ecole.rest;
import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.request.ParentDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.GroupeService;
import com.app.ecole.service.ParentService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ParentRestControler {
	@Autowired
	private ParentService parentService;

	@PostMapping(Utility.PARENTS)
	public Reponse getAddUser(@RequestBody ParentDtoRequest parentDtoRequest){
		Reponse resultatCreation = parentService.create(parentDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_PARENT_BY_ID)
	public Reponse changeUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =parentService.disableById(groupeID);
		return userUpdate ;
	}
	
	@GetMapping(Utility.PARENT_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =parentService.getById(groupeID);
		return userUpdate ;
    }

	@GetMapping(Utility.PARENTS)
	public Reponse getAllUsers()
	{
		Reponse   resultat = parentService.getAll();
		return resultat;
    }
	      
}
