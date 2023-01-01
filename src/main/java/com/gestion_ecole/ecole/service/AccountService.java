package com.gestion_ecole.ecole.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.entities.Code;
import com.gestion_ecole.ecole.entities.OutboundSMSMessageRequest;
import com.gestion_ecole.ecole.entities.OutboundSMSTextMessage;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Personnal;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.SmsMessage;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.Teacher;
import com.gestion_ecole.ecole.entities.User;
import com.gestion_ecole.ecole.repository.CodeRepository;
import com.gestion_ecole.ecole.repository.ParentRepository;
import com.gestion_ecole.ecole.repository.PersonnalRepository;
import com.gestion_ecole.ecole.repository.StudentRepository;
import com.gestion_ecole.ecole.repository.TeacherRepository;
import com.gestion_ecole.ecole.security.JwtTokenUtil;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class AccountService implements IAccountService{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	 
	@Autowired
	private UserDetailsService userDetailsService;
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PersonnalRepository personnalRepository; 
    @Autowired
    private StudentRepository studentRepository; 
    @Autowired
    private ParentRepository parentRepository; 
    @Autowired
    private TeacherRepository teacherRepository; 
    @Autowired
    private CodeRepository codeRepository; 
    @Autowired
   	private IEmailService emailService;
    @Autowired
   	private SmsService smsService;
   
	@Override
	public Reponse login_up(UserDtoRequest user) 
	{
		Reponse reponse = new Reponse();
	try
	{

		User personnalGot = personnalRepository.findByEmail(user.getEmail());
		User studentGot = studentRepository.findByEmail(user.getEmail());
		User parentGot = parentRepository.findByEmail(user.getEmail());
		User teacherGot = teacherRepository.findByEmail(user.getEmail());
        if(personnalGot == null &&  studentGot == null &&  parentGot == null && teacherGot == null )
        {
        	String pwdCryp = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(pwdCryp);		
			User userConverted = Utility.UserDtoRequestConvertToUser(user);

        	 if(userConverted instanceof Personnal )
             {
        		 User userSave = personnalRepository.save((Personnal)userConverted);
     			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));

             	
             }
             else if(userConverted instanceof Parent )
             {
            	 User userSave = parentRepository.save((Parent)userConverted);
      			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
             }
             else if(userConverted instanceof Student)
             {
            	 User userSave = studentRepository.save((Student)userConverted);
      			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
             }
             else if(userConverted instanceof Teacher )
             {
            	 User userSave = teacherRepository.save((Teacher)userConverted);
      			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
             }
        	reponse.setCode(200);
        	reponse.setMessage("Ce compte a été enregistré avec succès");
        }       
        else
        {
        	 if(user.isStatus()==false) 
    		{
             	//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	
	
    		}
        	 reponse.setCode(201);
         	 reponse.setMessage("Cet email est déjà utilisé  !");	
     
        }
		
	
	}
	catch (Exception e)
	{
		 reponse.setCode(500);
    	 reponse.setMessage("Une erreur interne est survenue  !");	
	} 
		
				return reponse ;
	}
	@Override
	public Reponse se_connecter(String phone,String password)
	{
		User userConnected=null;
		Code codeSaveOK=null;
		Reponse response = new Reponse();
		try
		{
			Reponse user = this.getUserByTelephone(phone);
				if((user != null)&&(bCryptPasswordEncoder.matches(password, ((User) user.getResult()).getPassword())))
				{
					Code codeSave = new Code();
					codeSave.setCode(new Random().ints(1, 9999).findFirst().getAsInt());
					codeSave.setTelephone(((User) user.getResult()).getTelephone());
					codeSave.setStatus(true);
					codeSave.setUser(((User) user.getResult()));
					 codeSaveOK=codeRepository.save(codeSave);	
					 
					((User) user.getResult()).getCodes().add(codeRepository.save(codeSaveOK));			
					 userConnected=(User) this.createOrUpdateUser(Utility.UserConvertToUserDtoRequest(((User) user.getResult()))).getResult();
					String contentMessageClient=" Bonjour Monsieur/Madame:"+((User) user.getResult()).getNom()+"\n" + 
	                        "Téléphone: "+((User) user.getResult()).getTelephone()+"\n" +
	                        "Email: "+((User) user.getResult()).getTelephone()+"\n" + 
	                        "Veuillez saisir le code  ci-dessous pour vous connectez "+"\n" + 
	                        "Code :"+codeSave.getCode()+"\n" ;                   				
					OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
					OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + ((User) user.getResult()).getTelephone(),outboundSMSTextMessageClient,"221" + ((User) user.getResult()).getTelephone(),"LAYDOU"); 
					SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
					Reponse smsReponse = smsService.sendMessage(smsMessageClient);   		

	               if(smsReponse.getCode() == 200)
	               {
	            	   ((User) user.getResult()).setMonToken(this.getToken(phone,password));
	    				response.setCode(200);
	    				response.setMessage("Message envoyé");
	    				response.setResult(((User) user.getResult()));		

	                }
	                else
	                {
	                	throw new Exception("Error to send sms ");
	                 }	
				
				}
				
				else
				{
					response.setCode(201);
					response.setMessage("La connexion a échoué !");
				}
		}
		catch (Exception e)
		{
			if(!(codeSaveOK == null))
			{
				 if(!(userConnected == null))
				{
					 userConnected.getCodes().remove(codeSaveOK);
					 this.createOrUpdateUser(Utility.UserConvertToUserDtoRequest(userConnected));
					 codeRepository.delete(codeSaveOK);
				}
				else
				{
					 codeRepository.delete(codeSaveOK);

				}
				
			} 
			response.setCode(500);
			response.setMessage("Une erreur interne est survenue !");
		}
	
		
		return response ;
	}
	
	@Override
	public String getToken(String phone , String password)
	{
      try {
		authenticate(phone,  password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		final UserDetails userDetails = userDetailsService
		.loadUserByUsername(phone);		
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
		
	}
	public  void authenticate(String phone, String password) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password));
		} catch (DisabledException e) {
		throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
		throw new Exception("INVALID_CREDENTIALS", e);
		}
		}
	@Override
	public void initAccount() {
		
		List<Personnal> users=personnalRepository.findAll();
			boolean arouna =false;

	    if(users.size() != 0)
	    {
	    	for (int i = 0; i < users.size(); i++) {
				switch (users.get(i).getTelephone()) {
				case "775073511":
					arouna=true;

					break;
			
				default:
					break;
				}
			}	
	    }
				 
		if(arouna == false) {
			User directeur =new Personnal();
			directeur.setTelephone("775073511");
			directeur.setEmail("sanouarouna90@gmail.com");
			directeur.setPassword("1234");
			directeur.setRole("Manager");
			login_up(Utility.UserConvertToUserDtoRequest(directeur));
			}
		
    	
	}
	
	@Override
	public Reponse createOrUpdateUser(UserDtoRequest user) {
		Reponse reponse = new Reponse();
	try
	{
		User personnalGot = personnalRepository.findByEmail(user.getEmail());
		User studentGot = studentRepository.findByEmail(user.getEmail());
		User parentGot = parentRepository.findByEmail(user.getEmail());
		User teacherGot = teacherRepository.findByEmail(user.getEmail());
		User userConverted = Utility.UserDtoRequestConvertToUser(user);
        if(personnalGot == null &&  studentGot == null &&  parentGot == null && teacherGot == null )
        {
        	Personnal personnal = personnalRepository.findById(user.getId()).get();
    		Student student = studentRepository.findById(user.getId()).get();
    		Parent parent = parentRepository.findById(user.getId()).get();
    		Teacher teacher = teacherRepository.findById(user.getId()).get();        	
        
        	 if(userConverted instanceof Personnal )
             {
        		 if(personnal!= null )
             	{
        			 personnal.setAdresse(user.getAdresse());
        			 personnal.setPrenom(user.getPrenom());
        			 personnal.setNom(user.getNom());
        			 personnal.setNumeroMatriciule(user.getNumeroMatriciule());
        			 personnal.setTypeDeRecrutement(user.getTypeDeRecrutement());
        			 personnal.setType(user.getType());
        			 personnal.setNaissance(user.getNaissance());       			
        			 personnal.setEmail(user.getEmail());
        			 personnal.setTelephone(user.getTelephone());
        			 personnal.setCompteBancaire(user.getCompteBancaire());
        			 personnal.setRole(user.getRole());
        			 User userSave = personnalRepository.save(personnal);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été modifié avec succès");

             	}
             	else
             	{
             		User userSave = personnalRepository.save((Personnal)userConverted);
         			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));      			               	
                	reponse.setMessage("Ce compte a été enregistré avec succès");
             	}
        		 reponse.setCode(200);
             	
             }
             else if(userConverted instanceof Parent )
             {
            	 if(parent!= null )
              	{
            		 parent.setAdresse(user.getAdresse());
            		 parent.setPrenom(user.getPrenom());
            		 parent.setNom(user.getNom());
            		 parent.setNumeroMatriciule(user.getNumeroMatriciule());
            		 parent.setTypeDeRecrutement(user.getTypeDeRecrutement());
            		 parent.setType(user.getType());
            		 parent.setNaissance(user.getNaissance());       			
            		 parent.setEmail(user.getEmail());
            		 parent.setTelephone(user.getTelephone());
            		 parent.setCompteBancaire(user.getCompteBancaire());
            		 parent.setRole(user.getRole());
            		 User userSave = parentRepository.save(parent);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été modifié avec succès");

              	}
              	else
              	{
              		User userSave = parentRepository.save((Parent)userConverted);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été enregistré avec succès");

              	}
            	 reponse.setCode(200);
            	 
             }
             else if(userConverted instanceof Student)
             {
            	 if(student!= null )
               	{
            		 student.setAdresse(user.getAdresse());
            		 student.setPrenom(user.getPrenom());
            		 student.setNom(user.getNom());
            		 student.setNumeroMatriciule(user.getNumeroMatriciule());
            		 student.setTypeDeRecrutement(user.getTypeDeRecrutement());
            		 student.setType(user.getType());
            		 student.setNaissance(user.getNaissance());       			
            		 student.setEmail(user.getEmail());
            		 student.setTelephone(user.getTelephone());
            		 student.setCompteBancaire(user.getCompteBancaire());
            		 student.setRole(user.getRole());
            		 User userSave = studentRepository.save(student);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été modifié avec succès");

               	}
               	else
               	{
               		User userSave = studentRepository.save((Student)userConverted);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été enregistré avec succès");

               	}
            	 reponse.setCode(200);
            	 
            	 
             }
             else
             {
            	 if(teacher!= null )
                	{
            		 teacher.setAdresse(user.getAdresse());
            		 teacher.setPrenom(user.getPrenom());
            		 teacher.setNom(user.getNom());
            		 teacher.setNumeroMatriciule(user.getNumeroMatriciule());
            		 teacher.setTypeDeRecrutement(user.getTypeDeRecrutement());
            		 teacher.setType(user.getType());
            		 teacher.setNaissance(user.getNaissance());       			
            		 teacher.setEmail(user.getEmail());
            		 teacher.setTelephone(user.getTelephone());
            		 teacher.setCompteBancaire(user.getCompteBancaire());

            		 teacher.setRole(user.getRole());
            		 User userSave = teacherRepository.save(teacher);
          			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                	reponse.setMessage("Ce compte a été modifié avec succès");

                	}
                	else
                	{
                		 User userSave = teacherRepository.save((Teacher)userConverted);
               			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
                    	reponse.setMessage("Ce compte a été enregistré avec succès");

                	}             	  
            	 
            	 reponse.setCode(200);
            	
             }
        	
        }       
        else
        {
        	 if(user.isStatus()==false) 
    		{
             	//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	
	
    		}
        	 reponse.setCode(201);
         	 reponse.setMessage("Cet email est déjà utilisé  !");	
     
        }
		
	
	}
	catch (Exception e)
	{
		 reponse.setCode(500);
    	 reponse.setMessage("Une erreur interne est survenue  !");	
	}
		
	return reponse ;
	}
	@Override
	public Reponse getUserById(Long id) {
		Reponse reponse = new Reponse();
		try
		{
			Personnal personnal = personnalRepository.findById(id).get();
    		Student student = studentRepository.findById(id).get();
    		Parent parent = parentRepository.findById(id).get();
    		Teacher teacher = teacherRepository.findById(id).get();      
    		if(personnal != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal));
            	reponse.setMessage("Ce compte a été retrouvé avec succès");
    		}
    		else if(student != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(student));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(parent !=null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(parent));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(teacher != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else
    		{
            	reponse.setMessage("Ce compte n'existe pas");

    		}
    		reponse.setCode(200);
    		
        
		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
    		reponse.setCode(500);

		}
		
		return reponse;
	}

	@Override
	public Reponse bloquerUser(Long id) {
		Reponse reponse = new Reponse();
		try
		{
			Personnal personnal = personnalRepository.findById(id).get();
    		Student student = studentRepository.findById(id).get();
    		Parent parent = parentRepository.findById(id).get();
    		Teacher teacher = teacherRepository.findById(id).get();      
    		if(personnal != null)
    		{
    			personnal.setStatus(false);
    			Personnal personnalS = personnalRepository.save(personnal);
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnalS));
            	reponse.setMessage("Ce compte a été bloqué avec succès");
    		}
    		else if(student != null)
    		{
    			student.setStatus(false);
    			Student studentS = studentRepository.save(student);
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(studentS));
            	reponse.setMessage("Ce compte a été bloqué avec succès");

    		}
    		else if(parent !=null)
    		{
    			parent.setStatus(false);
    			Parent parentS = parentRepository.save(parent);
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(parentS));
            	reponse.setMessage("Ce compte a été bloqué avec succès");

    		}
    		else if(teacher != null)
    		{
    			teacher.setStatus(false);
    			Teacher teacherS = teacherRepository.save(teacher);
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacherS));
            	reponse.setMessage("Ce compte a été bloqué avec succès");

    		}
    		else
    		{
            	reponse.setMessage("Ce compte n'existe pas");

    		}
    		reponse.setCode(200);
    		
        
		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
    		reponse.setCode(500);

		}
		
		return reponse;
	}
	@Override
	public Reponse getUserByEmail(String email) {
		Reponse reponse = new Reponse();
		try
		{
			User personnal = personnalRepository.findByEmail(email);
			User student = studentRepository.findByEmail(email);
			User parent = parentRepository.findByEmail(email);
			User teacher = teacherRepository.findByEmail(email);      
    		if(personnal != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal));
            	reponse.setMessage("Ce compte a été retrouvé avec succès");
    		}
    		else if(student != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(student));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(parent !=null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(parent));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(teacher != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else
    		{
            	reponse.setMessage("Ce compte n'existe pas");

    		}
    		reponse.setCode(200);
    		
        
		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
    		reponse.setCode(500);

		}
		
		return reponse;
	}
	@Override
	public Reponse activation(int code) {
		Reponse response = new Reponse();	
		try
	   {
			Code codeSave= codeRepository.findByCode(code).get();
			if(codeSave.getId() > 0 && codeSave.isStatus() == true )
			{
				User userGot = (User) this.getUserById(codeSave.getUser().getId()).getResult();
				
				if(userGot != null  && userGot.getId()>0)
				{
					
						Date dateFormatter = new Date();					
	                    codeSave.setDateConnexion(dateFormatter.getTime());	
	                    codeSave.setStatus(false);
						codeRepository.save(codeSave); 						
						userGot.setMonToken(this.getToken(userGot.getEmail(),userGot.getPassword()));	
						userGot.setStatus(true);
						this.createOrUpdateUser(Utility.UserConvertToUserDtoRequest(userGot));
						response.setCode(200);
						response.setMessage("L'activation a reussi");
						response.setResult(userGot);						
					
					
				}
				else
				{
					
					response.setCode(202);
					response.setMessage("L'activation a échoué");
					response.setResult(null);
				}

				
			}
			else
			{
				
				response.setCode(203);
				response.setMessage("Le code d'activation n'existe pas");
				response.setResult(null);
			}
			
			
		}
		catch(Exception e)
		{
			response.setCode(500);
			response.setMessage("Une erreur interne est survenue");
			response.setResult(null);		
			
		}
		return response;
	}
	@Override
	public Reponse getUserByTelephone(String telephone) {
		Reponse reponse = new Reponse();
		try
		{
			Personnal personnal = personnalRepository.findByTelephone(telephone);
    		Student student = studentRepository.findByTelephone(telephone);
    		Parent parent = parentRepository.findByTelephone(telephone);
    		Teacher teacher = teacherRepository.findByTelephone(telephone);      
    		if(personnal != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal));
            	reponse.setMessage("Ce compte a été retrouvé avec succès");
    		}
    		else if(student != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(student));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(parent !=null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(parent));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else if(teacher != null)
    		{
    			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher));

            	reponse.setMessage("Ce compte a été retrouvé avec succès");

    		}
    		else
    		{
            	reponse.setMessage("Ce compte n'existe pas");

    		}
    		reponse.setCode(200);
    		
        
		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
    		reponse.setCode(500);

		}
		
		return reponse;

	}
    	
	}
	
