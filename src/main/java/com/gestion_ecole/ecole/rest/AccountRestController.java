package com.gestion_ecole.ecole.rest;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.CodeDtoRequest;
import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.entities.Login;
import com.gestion_ecole.ecole.entities.MailSend;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IAccountService;
import com.gestion_ecole.ecole.service.IEmailService;
import com.gestion_ecole.ecole.utils.Utility;


@RestController
public class AccountRestController {
	Logger logger = LoggerFactory.getLogger(AccountRestController.class);
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IEmailService emailService;
	@PostMapping(Utility.DO_REGISTER)
	public boolean register( @RequestBody UserDtoRequest user) {
		boolean reponse =false;
		Reponse userAdd =accountService.login_up(user);
		if(userAdd !=null) {
			reponse =true;
		} 
		return reponse;
	}
	@PostMapping(Utility.DO_REGISTER_BY_ADMIN)
	public boolean registerByAmdin( @RequestBody UserDtoRequest user) {
		boolean reponse =false;
		String token = Utility.getTokenResetPassword();
		user.setPassword(token);
		Reponse userAdd =accountService.login_up(user);
		if(userAdd !=null) {
			reponse =true;
			try {
           // emailService.confirmedMessageAccountCreatedSuccess(new Login(user.getEmail(),token, user.getEmail()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reponse =false;
			}
			
		} 
		return reponse;
	}
	
	@PostMapping(Utility.DO_LOGIN)
	public Reponse verifiedAccount( @RequestBody Login  login) {
		Reponse user = accountService.se_connecter(login.getTelephone(), login.getPassword());		
		return user ;
	}
	@PostMapping(Utility.DO_ACTIVATION)
	public Reponse getUserById(@RequestBody CodeDtoRequest code){	

		Reponse	userUpdate =accountService.activation(code.getCode());		
		return userUpdate ;
    }
	@PostMapping(Utility.DO_CONTACTED)
	public void contacteNous(@RequestBody MailSend mail) throws MessagingException {	
		//emailService.sendContactEmail(mail);
    }
	@PostMapping(Utility.DO_FORGOT_PASSWORD)
	public long sendMail(@RequestBody Login login) throws MessagingException {
		Reponse user =accountService.getUserByEmail(login.getEmail());
		String token = Utility.getTokenResetPassword(); 
		long idUser  =0;
		if(user != null) {
			//idUser=accountService.updateResetPasswordToken(token,user.getEmail());
			//emailService.sendMailUpdatePassword(login, token);
		}
        return idUser;
    }
	
	@PostMapping(Utility.ADD_USER)
	public Reponse getAddOrUpdateUser( @RequestBody UserDtoRequest user){
		Reponse resultatCreation = accountService.createOrUpdateUser(user);
		return resultatCreation;
    }

	@PostMapping(Utility.UPDATE_USER)
	public Reponse getUpdateUser( @RequestBody StudentDtoRequest user){		
		Reponse	 userUpdate = accountService.createOrUpdateUser(user);
				
		return userUpdate;
    }
	
	@GetMapping(Utility.GET_USER_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") Long userId){		
		Reponse	userUpdate =accountService.getUserById(userId);		
		return userUpdate ;
    }
	
	@GetMapping(Utility.DELETE_USER_BY_ID)
	public Reponse getDeleteUser(@PathVariable(value = "id") Long userId){
				Reponse   resultat = accountService.bloquerUser(userId);		
			
					return resultat;
    }
	@GetMapping(Utility.GET_ALL_USERS)
	public Reponse getAllUsers(@PathVariable(value = "type") String  type){
				Reponse   resultat = accountService.getAllUsersByType(type);		
			
					return resultat;
    }
		
}
