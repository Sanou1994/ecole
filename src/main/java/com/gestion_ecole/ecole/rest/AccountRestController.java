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
import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.entities.Login;
import com.gestion_ecole.ecole.entities.MailSend;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IAccountService;
import com.gestion_ecole.ecole.service.IEmailService;
import com.gestion_ecole.ecole.service.IParentService;
import com.gestion_ecole.ecole.service.IPersonnalService;
import com.gestion_ecole.ecole.service.IStudentService;
import com.gestion_ecole.ecole.service.ITeacherService;
import com.gestion_ecole.ecole.utils.Utility;


@RestController
public class AccountRestController {
	Logger logger = LoggerFactory.getLogger(AccountRestController.class);
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IPersonnalService personnalService;
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IParentService parentService;
	@Autowired
	private IEmailService emailService;
		
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
	
	@PostMapping(Utility.ADD_PERSONNAL)
	public Reponse getAddOrUpdatePersonnal( @RequestBody PersonnalDtoRequest personnal){
		Reponse reponse = personnalService.createOrUpdatePersonnal(personnal);
		return reponse;
    }
	@PostMapping(Utility.ADD_TEACHER)
	public Reponse getAddOrUpdateTeacher( @RequestBody TeacherDtoRequest teacher){
		Reponse reponse = teacherService.createOrUpdateTeacher(teacher);
		return reponse;
    }
	@PostMapping(Utility.ADD_STUDENT)
	public Reponse getAddOrUpdateStudent( @RequestBody StudentDtoRequest student){
		Reponse reponse = studentService.createOrUpdateStudent(student);
		return reponse;
    }
	@PostMapping(Utility.ADD_PARENT)
	public Reponse getAddOrUpdateParent( @RequestBody ParentDtoRequest parent){
		Reponse reponse = parentService.createOrUpdateParent(parent);
		return reponse;
    }
	
	@GetMapping(Utility.GET_USER_BY_ID)
	public Reponse getUserById(@PathVariable(value = "type") String  type,@PathVariable(value = "id") Long userId){		
		Reponse	userUpdate =accountService.getUserById(userId,type);		
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
