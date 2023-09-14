package com.app.ecole.rest;
import com.app.ecole.dto.request.Login;
import com.app.ecole.dto.request.UserDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.IAccountService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountRestControler {
	@Autowired
	private IAccountService accountService;

	@PostMapping(Utility.USERS)
	public Reponse getAddUser(@RequestBody UserDtoRequest user){
		Reponse resultatCreation = accountService.login_up(user);
		return resultatCreation;
    }
	@PostMapping(Utility.LOGIN)
	public Reponse login(@RequestBody Login login){
		Reponse resultatCreation = accountService.se_connecter(login.getEmail(),login.getPassword());
		return resultatCreation;
	}

	
	@GetMapping(Utility.USER_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID droitID){
		Reponse	userUpdate =accountService.getUserById(droitID);
		return userUpdate ;
    }
	@GetMapping(Utility.CHANGE_STATUS_USER_BY_ID)
	public Reponse deleteUserById(@PathVariable(value = "id") UUID droitID){
		Reponse	userUpdate =accountService.disableUserById(droitID);
		return userUpdate ;
	}



	@GetMapping(Utility.USERS)
	public Reponse getAllUsers()
	{
		Reponse   resultat = accountService.getAllUsers();
		return resultat;
    }
	      
}
