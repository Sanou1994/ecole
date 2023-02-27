package com.gestion_ecole.ecole.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
import com.gestion_ecole.ecole.dto.response.ParentDtoResponse;
import com.gestion_ecole.ecole.dto.response.PersonnalDtoResponse;
import com.gestion_ecole.ecole.dto.response.StudentDtoResponse;
import com.gestion_ecole.ecole.dto.response.TeacherDtoResponse;
import com.gestion_ecole.ecole.dto.response.UserDtoResponse;
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

		Reponse userByEmail=this.getUserByEmail(user.getEmail());
		Reponse userByTelephone=this.getUserByTelephone(user.getTelephone());		
		String pwdCryp = bCryptPasswordEncoder.encode(user.getPassword());
		switch(userByEmail.getCode())
		{
		  case 200:
			    if(((UserDtoResponse)userByEmail.getResult()).isStatus()==false) 
				{
					//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	
			    	reponse.setCode(201);
					reponse.setMessage("Cet email est déjà utilisé  !");
				}
			    else if(user.getId() != null && user.getId() != 0)
			    {
			    	switch(userByTelephone.getCode())
					{
					  case 200:
						    if(((UserDtoResponse)userByTelephone.getResult()).isStatus()==false) 
							{
								//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	

							}
						    else if(user.getId() != null && user.getId() != 0)
						    {
						    	
									Optional<Personnal> personnal = personnalRepository.findById(user.getId());
									Optional<Student> student = studentRepository.findById(user.getId());
									Optional<Parent> parent = parentRepository.findById(user.getId());
									Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

									if(user.getTypeUser().equals("PERSONNAL"))
									{
										if(personnal.isPresent() )
										{
											personnal.get().setAdresse(user.getAdresse());
											personnal.get().setPrenom(user.getPrenom());
											personnal.get().setNom(user.getNom());
											personnal.get().setLieu_naissance(user.getLieu_naissance());
											personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
											personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											personnal.get().setContratID(user.getContratID());
											personnal.get().setType(user.getType());
											personnal.get().setNaissance(user.getNaissance());       			
											personnal.get().setEmail(user.getEmail());
											personnal.get().setTelephone(user.getTelephone());
											personnal.get().setCompteBancaire(user.getCompteBancaire());
											personnal.get().setRole(user.getRole());
											personnal.get().setNationalite(user.getNationalite());
											User userSave = personnalRepository.save(personnal.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");
											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}
									}
									else if(user.getTypeUser().equals("PARENT"))
									{
										if(parent.isPresent() )
										{
											parent.get().setAdresse(user.getAdresse());
											parent.get().setPrenom(user.getPrenom());
											parent.get().setNom(user.getNom());
											parent.get().setContratID(user.getContratID());
											parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
											parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											parent.get().setType(user.getType());
											parent.get().setNaissance(user.getNaissance());
											parent.get().setLieu_naissance(user.getLieu_naissance());
											parent.get().setEmail(user.getEmail());
											parent.get().setTelephone(user.getTelephone());
											parent.get().setCompteBancaire(user.getCompteBancaire());
											parent.get().setRole(user.getRole());
											parent.get().setNationalite(user.getNationalite());
											User userSave = parentRepository.save(parent.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");
											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}

									}
									else if(user.getTypeUser().equals("STUDENT"))
									{
										if(student.isPresent() )
										{
											student.get().setName_logo(user.getName_logo());
											student.get().setPrenom(user.getPrenom());
											student.get().setNom(user.getNom());
											student.get().setContratID(user.getContratID());
											student.get().setNumeroMatriciule(user.getNumeroMatriciule());
											student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											student.get().setType(user.getType());
											student.get().setNaissance(user.getNaissance());   
											student.get().setNationalite(user.getNationalite());
											student.get().setEmail(user.getEmail());
											student.get().setTelephone(user.getTelephone());
											student.get().setLieu_naissance(user.getLieu_naissance());
											student.get().setCompteBancaire(user.getCompteBancaire());
											student.get().setRole(user.getRole());
											User userSave = studentRepository.save(student.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");

											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}


									}
									else
									{
										if(teacher.isPresent() )
										{
											teacher.get().setAdresse(user.getAdresse());
											teacher.get().setPrenom(user.getPrenom());
											teacher.get().setNom(user.getNom());
											teacher.get().setContratID(user.getContratID());
											teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
											teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											teacher.get().setType(user.getType());
											teacher.get().setNaissance(user.getNaissance());
											teacher.get().setNationalite(user.getNationalite());
											teacher.get().setEmail(user.getEmail());
											teacher.get().setTelephone(user.getTelephone());
											teacher.get().setCompteBancaire(user.getCompteBancaire());
											teacher.get().setLieu_naissance(user.getLieu_naissance());
											teacher.get().setRole(user.getRole());
											User userSave = teacherRepository.save(teacher.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");

											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}

									}		

								
						    }
						    else
						    {
						    	reponse.setCode(201);
								reponse.setMessage("Cet numéro est déjà utilisé  !");
						    }
						    
					    break;
					  case 201:
					  {
						  
						  
							if(  user.getId() != null && user.getId() != 0)
							{
								Optional<Personnal> personnal = personnalRepository.findById(user.getId());
								Optional<Student> student = studentRepository.findById(user.getId());
								Optional<Parent> parent = parentRepository.findById(user.getId());
								Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

								if(user.getTypeUser().equals("PERSONNAL"))
								{
									if(personnal.isPresent() )
									{
										personnal.get().setAdresse(user.getAdresse());
								    	personnal.get().setPrenom(user.getPrenom());
										personnal.get().setNom(user.getNom());
										personnal.get().setContratID(user.getContratID());
										personnal.get().setLieu_naissance(user.getLieu_naissance());
										personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
										personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										personnal.get().setType(user.getType());
										personnal.get().setNaissance(user.getNaissance());       			
										personnal.get().setEmail(user.getEmail());
										personnal.get().setTelephone(user.getTelephone());
										personnal.get().setCompteBancaire(user.getCompteBancaire());
										personnal.get().setRole(user.getRole());
										personnal.get().setNationalite(user.getNationalite());
										User userSave = personnalRepository.save(personnal.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}
								}
								else if(user.getTypeUser().equals("PARENT"))
								{
									if(parent.isPresent() )
									{
										parent.get().setAdresse(user.getAdresse());
										parent.get().setPrenom(user.getPrenom());
										parent.get().setNom(user.getNom());
										parent.get().setContratID(user.getContratID());
										parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
										parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										parent.get().setType(user.getType());
										parent.get().setNaissance(user.getNaissance());
										parent.get().setLieu_naissance(user.getLieu_naissance());
										parent.get().setEmail(user.getEmail());
										parent.get().setTelephone(user.getTelephone());
										parent.get().setCompteBancaire(user.getCompteBancaire());
										parent.get().setRole(user.getRole());
										parent.get().setNationalite(user.getNationalite());
										User userSave = parentRepository.save(parent.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}
								else if(user.getTypeUser().equals("STUDENT"))
								{
									if(student.isPresent() )
									{
										student.get().setAdresse(user.getAdresse());
										student.get().setPrenom(user.getPrenom());
										student.get().setNom(user.getNom());
										student.get().setContratID(user.getContratID());
										student.get().setNumeroMatriciule(user.getNumeroMatriciule());
										student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										student.get().setType(user.getType());
										student.get().setNaissance(user.getNaissance());   
										student.get().setNationalite(user.getNationalite());
										student.get().setEmail(user.getEmail());
										student.get().setTelephone(user.getTelephone());
										student.get().setLieu_naissance(user.getLieu_naissance());
										student.get().setCompteBancaire(user.getCompteBancaire());
										student.get().setRole(user.getRole());
										User userSave = studentRepository.save(student.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}


								}
								else
								{
									if(teacher.isPresent() )
									{
										teacher.get().setAdresse(user.getAdresse());
										teacher.get().setPrenom(user.getPrenom());
										teacher.get().setNom(user.getNom());
										teacher.get().setContratID(user.getContratID());
										teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
										teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										teacher.get().setType(user.getType());
										teacher.get().setNaissance(user.getNaissance());
										teacher.get().setNationalite(user.getNationalite());
										teacher.get().setEmail(user.getEmail());
										teacher.get().setTelephone(user.getTelephone());
										teacher.get().setCompteBancaire(user.getCompteBancaire());
										teacher.get().setLieu_naissance(user.getLieu_naissance());
										teacher.get().setRole(user.getRole());
										User userSave = teacherRepository.save(teacher.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}			

							}
							else
							{
								User userConverted = Utility.UserDtoRequestConvertToUser(user);			

								user.setPassword(pwdCryp);
								if(user.getTypeUser().equals("PERSONNAL"))
								{

									User userSave = personnalRepository.save((Personnal)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));      			               	
									reponse.setMessage("Ce compte a été enregistré avec succès");

									reponse.setCode(200);

								}
								else if(user.getTypeUser().equals("PARENT"))
								{

									User userSave = parentRepository.save((Parent)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès");
									reponse.setCode(200);



								}
								else if(user.getTypeUser().equals("STUDENT"))
								{

									User userSave = studentRepository.save((Student)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès");
									reponse.setCode(200);




								}
								else
								{
									Teacher userSave = teacherRepository.save(((Teacher)userConverted));
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès : TE" +((Teacher)userConverted).getNiveauEtude());
									reponse.setCode(200);

								}           	  


							}		  
						  
					  }
					    break;
					  default:
						  reponse=userByTelephone;
					}	
			    }
			    else
			    {
			    	reponse.setCode(201);
					reponse.setMessage("Cet email est déjà utilisé  !");
			    }
				
		    break;
		  case 201:
		  {
			  switch(userByTelephone.getCode())
				{
				  case 200:
					    if(user.isStatus()==false) 
						{
							//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	

						}
					    else if(user.getId() != null && user.getId() != 0)
					    {
					    	
								Optional<Personnal> personnal = personnalRepository.findById(user.getId());
								Optional<Student> student = studentRepository.findById(user.getId());
								Optional<Parent> parent = parentRepository.findById(user.getId());
								Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

								if(user.getTypeUser().equals("PERSONNAL"))
								{
									if(personnal.isPresent() )
									{
										personnal.get().setAdresse(user.getAdresse());
										personnal.get().setPrenom(user.getPrenom());
										personnal.get().setContratID(user.getContratID());
										personnal.get().setNom(user.getNom());
										personnal.get().setLieu_naissance(user.getLieu_naissance());
										personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
										personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										personnal.get().setType(user.getType());
										personnal.get().setNaissance(user.getNaissance());       			
										personnal.get().setEmail(user.getEmail());
										personnal.get().setTelephone(user.getTelephone());
										personnal.get().setCompteBancaire(user.getCompteBancaire());
										personnal.get().setRole(user.getRole());
										personnal.get().setNationalite(user.getNationalite());
										User userSave = personnalRepository.save(personnal.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}
								}
								else if(user.getTypeUser().equals("PARENT"))
								{
									if(parent.isPresent() )
									{
										parent.get().setAdresse(user.getAdresse());
										parent.get().setPrenom(user.getPrenom());
										parent.get().setNom(user.getNom());
										parent.get().setContratID(user.getContratID());
										parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
										parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										parent.get().setType(user.getType());
										parent.get().setNaissance(user.getNaissance());
										parent.get().setLieu_naissance(user.getLieu_naissance());
										parent.get().setEmail(user.getEmail());
										parent.get().setTelephone(user.getTelephone());
										parent.get().setCompteBancaire(user.getCompteBancaire());
										parent.get().setRole(user.getRole());
										parent.get().setNationalite(user.getNationalite());
										User userSave = parentRepository.save(parent.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}
								else if(user.getTypeUser().equals("STUDENT"))
								{
									if(student.isPresent() )
									{
										student.get().setAdresse(user.getAdresse());
										student.get().setPrenom(user.getPrenom());
										student.get().setNom(user.getNom());
										student.get().setContratID(user.getContratID());
										student.get().setNumeroMatriciule(user.getNumeroMatriciule());
										student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										student.get().setType(user.getType());
										student.get().setNaissance(user.getNaissance());   
										student.get().setNationalite(user.getNationalite());
										student.get().setEmail(user.getEmail());
										student.get().setTelephone(user.getTelephone());
										student.get().setLieu_naissance(user.getLieu_naissance());
										student.get().setCompteBancaire(user.getCompteBancaire());
										student.get().setRole(user.getRole());
										User userSave = studentRepository.save(student.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}


								}
								else
								{
									if(teacher.isPresent() )
									{
										teacher.get().setAdresse(user.getAdresse());
										teacher.get().setPrenom(user.getPrenom());
										teacher.get().setNom(user.getNom());
										teacher.get().setContratID(user.getContratID());
										teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
										teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										teacher.get().setType(user.getType());
										teacher.get().setNaissance(user.getNaissance());
										teacher.get().setNationalite(user.getNationalite());
										teacher.get().setEmail(user.getEmail());
										teacher.get().setTelephone(user.getTelephone());
										teacher.get().setCompteBancaire(user.getCompteBancaire());
										teacher.get().setLieu_naissance(user.getLieu_naissance());
										teacher.get().setRole(user.getRole());
										User userSave = teacherRepository.save(teacher.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}		

							
					    }
					    else
					    {
					    	reponse.setCode(201);
							reponse.setMessage("Cet numéro est déjà utilisé  !");
					    }
					    
				    break;
				  case 201:
				  {
					  
					  
						if(  user.getId() != null && user.getId() != 0)
						{
							Optional<Personnal> personnal = personnalRepository.findById(user.getId());
							Optional<Student> student = studentRepository.findById(user.getId());
							Optional<Parent> parent = parentRepository.findById(user.getId());
							Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

							if(user.getTypeUser().equals("PERSONNAL"))
							{
								if(personnal.isPresent() )
								{
									personnal.get().setAdresse(user.getAdresse());
									personnal.get().setPrenom(user.getPrenom());									
									personnal.get().setContratID(user.getContratID());
									personnal.get().setNom(user.getNom());
									personnal.get().setLieu_naissance(user.getLieu_naissance());
									personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
									personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									personnal.get().setType(user.getType());
									personnal.get().setNaissance(user.getNaissance());       			
									personnal.get().setEmail(user.getEmail());
									personnal.get().setTelephone(user.getTelephone());
									personnal.get().setCompteBancaire(user.getCompteBancaire());
									personnal.get().setRole(user.getRole());
									personnal.get().setNationalite(user.getNationalite());
									User userSave = personnalRepository.save(personnal.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");
									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}
							}
							else if(user.getTypeUser().equals("PARENT"))
							{
								if(parent.isPresent() )
								{
									parent.get().setAdresse(user.getAdresse());
									parent.get().setPrenom(user.getPrenom());
									parent.get().setNom(user.getNom());
									parent.get().setContratID(user.getContratID());
									parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
									parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									parent.get().setType(user.getType());
									parent.get().setNaissance(user.getNaissance());
									parent.get().setLieu_naissance(user.getLieu_naissance());
									parent.get().setEmail(user.getEmail());
									parent.get().setTelephone(user.getTelephone());
									parent.get().setCompteBancaire(user.getCompteBancaire());
									parent.get().setRole(user.getRole());
									parent.get().setNationalite(user.getNationalite());
									User userSave = parentRepository.save(parent.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");
									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}

							}
							else if(user.getTypeUser().equals("STUDENT"))
							{
								if(student.isPresent() )
								{
									student.get().setAdresse(user.getAdresse());
									student.get().setPrenom(user.getPrenom());
									student.get().setNom(user.getNom());
									student.get().setContratID(user.getContratID());
									student.get().setNumeroMatriciule(user.getNumeroMatriciule());
									student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									student.get().setType(user.getType());
									student.get().setNaissance(user.getNaissance());   
									student.get().setNationalite(user.getNationalite());
									student.get().setEmail(user.getEmail());
									student.get().setTelephone(user.getTelephone());
									student.get().setLieu_naissance(user.getLieu_naissance());
									student.get().setCompteBancaire(user.getCompteBancaire());
									student.get().setRole(user.getRole());
									User userSave = studentRepository.save(student.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");

									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}


							}
							else
							{
								if(teacher.isPresent() )
								{
									teacher.get().setAdresse(user.getAdresse());
									teacher.get().setPrenom(user.getPrenom());
									teacher.get().setNom(user.getNom());
									teacher.get().setContratID(user.getContratID());
									teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
									teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									teacher.get().setType(user.getType());
									teacher.get().setNaissance(user.getNaissance());
									teacher.get().setNationalite(user.getNationalite());
									teacher.get().setEmail(user.getEmail());
									teacher.get().setTelephone(user.getTelephone());
									teacher.get().setCompteBancaire(user.getCompteBancaire());
									teacher.get().setLieu_naissance(user.getLieu_naissance());
									teacher.get().setRole(user.getRole());
									User userSave = teacherRepository.save(teacher.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");

									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}

							}			

						}
						else
						{
							User userConverted = Utility.UserDtoRequestConvertToUser(user);			

							user.setPassword(pwdCryp);

							if(user.getTypeUser().equals("PERSONNAL"))
							{

								User userSave = personnalRepository.save((Personnal)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));      			               	
								reponse.setMessage("Ce compte a été enregistré avec succès");

								reponse.setCode(200);

							}
							else if(user.getTypeUser().equals("PARENT"))
							{

								User userSave = parentRepository.save((Parent)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès");
								reponse.setCode(200);



							}
							else if(user.getTypeUser().equals("STUDENT"))
							{

								User userSave = studentRepository.save((Student)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès");
								reponse.setCode(200);




							}
							else
							{
								Teacher userSave = teacherRepository.save(((Teacher)userConverted));
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès : TE" +((Teacher)userConverted).getNiveauEtude());
								reponse.setCode(200);

							}           	  


						}		  
					  
				  }
				    break;
				  default:
					  reponse=userByTelephone;
				}
		  }
		    // code block
		    break;
		  default:
			  reponse=userByEmail;
		}
		
	}
	catch (Exception e)
	{
		 reponse.setCode(500);
    	 reponse.setMessage("Une erreur interne est survenue coté serveur  !");	
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


			if(user.getCode() == 200)
			{			


				UserDtoResponse userConvert=null;
				if(user.getResult() instanceof PersonnalDtoResponse )
				{
					userConvert=(PersonnalDtoResponse) user.getResult();

				}
				else if(user.getResult() instanceof ParentDtoResponse )
				{

					userConvert=(ParentDtoResponse) user.getResult();


				}
				else if(user.getResult() instanceof StudentDtoResponse)
				{
					userConvert=(StudentDtoResponse) user.getResult();

				}
				else
				{
					userConvert=(TeacherDtoResponse) user.getResult();

				}

				if((bCryptPasswordEncoder.matches(password, userConvert.getPassword())))
				{
					User userC=Utility.UserDtoResponseConvertToUser(userConvert);
					Code codeSave = new Code();
					codeSave.setCode(new Random().ints(1, 9999).findFirst().getAsInt());
					codeSave.setTelephone(userConvert.getTelephone());
					codeSave.setStatus(true);
					codeSave.setType(userConvert.getRole());
					codeSave.setUserID(userC.getId());
					codeSaveOK=codeRepository.save(codeSave);					 
					userC.getCodes().add(codeRepository.save(codeSaveOK));			
					userConnected=(User) this.createOrUpdateUser(Utility.UserConvertToUserDtoRequest(userC)).getResult();
					String contentMessageClient=" Bonjour Monsieur/Madame:"+userC.getNom()+"\n" + 
							"Téléphone: "+userC.getTelephone()+"\n" +
							"Email: "+userC.getTelephone()+"\n" + 
							"Veuillez saisir le code  ci-dessous pour vous connectez "+"\n" + 
							"Code :"+codeSave.getCode()+"\n" ;                   				
					OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
					OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + userC.getTelephone(),outboundSMSTextMessageClient,"221" + userC.getTelephone(),"LAYDOU"); 
					SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
					//	Reponse smsReponse = smsService.sendMessage(smsMessageClient);   		

					/*    if(smsReponse.getCode() == 200)
	               {
	            	   ((User) user.getResult()).setMonToken(this.getToken(phone,password));
	    				response.setCode(200);
	    				response.setMessage("Message envoyé");
	    				response.setResult(((User) user.getResult()));		

	                }
	                else
	                {
	                //	throw new Exception("Error to send sms ");
	                }	*/
					response.setCode(200);
					response.setMessage("La connexion a reussi !");
					response.setResult(userC);

				}

				else
				{
					response.setCode(201);
					response.setMessage("La connexion a échoué !");
				}
			}
			else
			{
				response.setCode(user.getCode());
				response.setMessage(user.getMessage());

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
			response.setMessage("Une erreur serveur est survenue !");
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
	public void initAccount()
	{

		List<Personnal> users=  personnalRepository.findAll();
		boolean arouna =false;

		if(users.size() != 0)
		{
			for (int i = 0; i < users.size(); i++)
			{
				switch (users.get(i).getTelephone())
				{
					case "775073511":
						arouna=true;
	
						break;
	
					default:
						break;
				}
			}	
		}

		if(arouna == false) 
		{
			
			UserDtoRequest directeur =new UserDtoRequest();
			directeur.setTelephone("775073511");
			directeur.setEmail("sanouarouna90@gmail.com");
			directeur.setPassword("1234");
			directeur.setRole("Manager");
			directeur.setTypeUser("PERSONNAL");
			login_up(directeur);
		}


	}

	@Override
	public Reponse createOrUpdateUser(UserDtoRequest user) {
		Reponse reponse = new Reponse();
		try
		{

		Reponse userByEmail=this.getUserByEmail(user.getEmail());
		Reponse userByTelephone=this.getUserByTelephone(user.getTelephone());		
		User userConverted = Utility.UserDtoRequestConvertToUser(user);
		
		switch(userByEmail.getCode())
		{
		  case 200:
			    if(((UserDtoResponse)userByEmail.getResult()).isStatus()==false) 
				{
					//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	
			    	reponse.setCode(201);
					reponse.setMessage("Cet email est déjà utilisé  !");
				}
			    else if(user.getId() != null && user.getId() != 0)
			    {
			    	switch(userByTelephone.getCode())
					{
					  case 200:
						    if(((UserDtoResponse)userByTelephone.getResult()).isStatus()==false) 
							{
								//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	

							}
						    else if(user.getId() != null && user.getId() != 0)
						    {
						    	
									Optional<Personnal> personnal = personnalRepository.findById(user.getId());
									Optional<Student> student = studentRepository.findById(user.getId());
									Optional<Parent> parent = parentRepository.findById(user.getId());
									Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

									if(user.getTypeUser().equals("PERSONNAL"))
									{
										if(personnal.isPresent() )
										{
											personnal.get().setAdresse(user.getAdresse());
											personnal.get().setPrenom(user.getPrenom());
											personnal.get().setNom(user.getNom());
											personnal.get().setLieu_naissance(user.getLieu_naissance());
											personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
											personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											personnal.get().setContratID(user.getContratID());
											personnal.get().setType(user.getType());
											personnal.get().setNaissance(user.getNaissance());       			
											personnal.get().setEmail(user.getEmail());
											personnal.get().setTelephone(user.getTelephone());
											personnal.get().setCompteBancaire(user.getCompteBancaire());
											personnal.get().setRole(user.getRole());
											personnal.get().setNationalite(user.getNationalite());
											User userSave = personnalRepository.save(personnal.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");
											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}
									}
									else if(user.getTypeUser().equals("PARENT"))
									{
										if(parent.isPresent() )
										{
											parent.get().setAdresse(user.getAdresse());
											parent.get().setPrenom(user.getPrenom());
											parent.get().setNom(user.getNom());
											parent.get().setContratID(user.getContratID());
											parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
											parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											parent.get().setType(user.getType());
											parent.get().setNaissance(user.getNaissance());
											parent.get().setLieu_naissance(user.getLieu_naissance());
											parent.get().setEmail(user.getEmail());
											parent.get().setTelephone(user.getTelephone());
											parent.get().setCompteBancaire(user.getCompteBancaire());
											parent.get().setRole(user.getRole());
											parent.get().setNationalite(user.getNationalite());
											User userSave = parentRepository.save(parent.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");
											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}

									}
									else if(user.getTypeUser().equals("STUDENT"))
									{
										if(student.isPresent() )
										{
											student.get().setName_logo(user.getName_logo());
											student.get().setPrenom(user.getPrenom());
											student.get().setNom(user.getNom());
											student.get().setContratID(user.getContratID());
											student.get().setNumeroMatriciule(user.getNumeroMatriciule());
											student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											student.get().setType(user.getType());
											student.get().setNaissance(user.getNaissance());   
											student.get().setNationalite(user.getNationalite());
											student.get().setEmail(user.getEmail());
											student.get().setTelephone(user.getTelephone());
											student.get().setLieu_naissance(user.getLieu_naissance());
											student.get().setCompteBancaire(user.getCompteBancaire());
											student.get().setRole(user.getRole());
											User userSave = studentRepository.save(student.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");

											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}


									}
									else
									{
										if(teacher.isPresent() )
										{
											teacher.get().setNiveauEtude(teacher.get().getNiveauEtude());
											teacher.get().setAdresse(user.getAdresse());
											teacher.get().setPrenom(user.getPrenom());
											teacher.get().setNom(user.getNom());
											teacher.get().setContratID(user.getContratID());
											teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
											teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
											teacher.get().setType(user.getType());
											teacher.get().setNaissance(user.getNaissance());
											teacher.get().setNationalite(user.getNationalite());
											teacher.get().setEmail(user.getEmail());
											teacher.get().setTelephone(user.getTelephone());
											teacher.get().setCompteBancaire(user.getCompteBancaire());
											teacher.get().setLieu_naissance(user.getLieu_naissance());
											teacher.get().setRole(user.getRole());
											User userSave = teacherRepository.save(teacher.get());
											reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
											reponse.setMessage("Ce compte a été modifié avec succès");

											reponse.setCode(200);

										}
										else
										{
											reponse.setMessage("Ce compte n'existe plus");
											reponse.setCode(201);

										}

									}		

								
						    }
						    else
						    {
						    	reponse.setCode(201);
								reponse.setMessage("Cet numéro est déjà utilisé  !");
						    }
						    
					    break;
					  case 201:
					  {
						  
						  
							if(  user.getId() != null && user.getId() != 0)
							{
								Optional<Personnal> personnal = personnalRepository.findById(user.getId());
								Optional<Student> student = studentRepository.findById(user.getId());
								Optional<Parent> parent = parentRepository.findById(user.getId());
								Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

								if(user.getTypeUser().equals("PERSONNAL"))
								{
									if(personnal.isPresent() )
									{
										personnal.get().setAdresse(user.getAdresse());
								    	personnal.get().setPrenom(user.getPrenom());
										personnal.get().setNom(user.getNom());
										personnal.get().setContratID(user.getContratID());
										personnal.get().setLieu_naissance(user.getLieu_naissance());
										personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
										personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										personnal.get().setType(user.getType());
										personnal.get().setNaissance(user.getNaissance());       			
										personnal.get().setEmail(user.getEmail());
										personnal.get().setTelephone(user.getTelephone());
										personnal.get().setCompteBancaire(user.getCompteBancaire());
										personnal.get().setRole(user.getRole());
										personnal.get().setNationalite(user.getNationalite());
										User userSave = personnalRepository.save(personnal.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}
								}
								else if(user.getTypeUser().equals("PARENT"))
								{
									if(parent.isPresent() )
									{
										parent.get().setAdresse(user.getAdresse());
										parent.get().setPrenom(user.getPrenom());
										parent.get().setNom(user.getNom());
										parent.get().setContratID(user.getContratID());
										parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
										parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										parent.get().setType(user.getType());
										parent.get().setNaissance(user.getNaissance());
										parent.get().setLieu_naissance(user.getLieu_naissance());
										parent.get().setEmail(user.getEmail());
										parent.get().setTelephone(user.getTelephone());
										parent.get().setCompteBancaire(user.getCompteBancaire());
										parent.get().setRole(user.getRole());
										parent.get().setNationalite(user.getNationalite());
										User userSave = parentRepository.save(parent.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}
								else if(user.getTypeUser().equals("STUDENT"))
								{
									if(student.isPresent() )
									{
										student.get().setAdresse(user.getAdresse());
										student.get().setPrenom(user.getPrenom());
										student.get().setNom(user.getNom());
										student.get().setContratID(user.getContratID());
										student.get().setNumeroMatriciule(user.getNumeroMatriciule());
										student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										student.get().setType(user.getType());
										student.get().setNaissance(user.getNaissance());   
										student.get().setNationalite(user.getNationalite());
										student.get().setEmail(user.getEmail());
										student.get().setTelephone(user.getTelephone());
										student.get().setLieu_naissance(user.getLieu_naissance());
										student.get().setCompteBancaire(user.getCompteBancaire());
										student.get().setRole(user.getRole());
										User userSave = studentRepository.save(student.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}


								}
								else
								{
									if(teacher.isPresent() )
									{
										teacher.get().setNiveauEtude(teacher.get().getNiveauEtude());
										teacher.get().setAdresse(user.getAdresse());
										teacher.get().setPrenom(user.getPrenom());
										teacher.get().setNom(user.getNom());
										teacher.get().setContratID(user.getContratID());
										teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
										teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										teacher.get().setType(user.getType());
										teacher.get().setNaissance(user.getNaissance());
										teacher.get().setNationalite(user.getNationalite());
										teacher.get().setEmail(user.getEmail());
										teacher.get().setTelephone(user.getTelephone());
										teacher.get().setCompteBancaire(user.getCompteBancaire());
										teacher.get().setLieu_naissance(user.getLieu_naissance());
										teacher.get().setRole(user.getRole());
										User userSave = teacherRepository.save(teacher.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}			

							}
							else
							{
								
								if(user.getTypeUser().equals("PERSONNAL"))
								{

									User userSave = personnalRepository.save((Personnal)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));      			               	
									reponse.setMessage("Ce compte a été enregistré avec succès");

									reponse.setCode(200);

								}
								else if(user.getTypeUser().equals("PARENT"))
								{

									User userSave = parentRepository.save((Parent)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès");
									reponse.setCode(200);



								}
								else if(user.getTypeUser().equals("STUDENT"))
								{

									User userSave = studentRepository.save((Student)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès");
									reponse.setCode(200);




								}
								else
								{

									User userSave = teacherRepository.save((Teacher)userConverted);
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été enregistré avec succès");
									reponse.setCode(200);

								}           	  


							}		  
						  
					  }
					    break;
					  default:
						  reponse=userByTelephone;
					}	
			    }
			    else
			    {
			    	reponse.setCode(201);
					reponse.setMessage("Cet email est déjà utilisé  !");
			    }
				
		    break;
		  case 201:
		  {
			  switch(userByTelephone.getCode())
				{
				  case 200:
					    if(user.isStatus()==false) 
						{
							//emailService.sendEmailToActivateAccount(userGot.getNom(), userGot.getEmail());	

						}
					    else if(user.getId() != null && user.getId() != 0)
					    {
					    	
								Optional<Personnal> personnal = personnalRepository.findById(user.getId());
								Optional<Student> student = studentRepository.findById(user.getId());
								Optional<Parent> parent = parentRepository.findById(user.getId());
								Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

								if(user.getTypeUser().equals("PERSONNAL"))
								{
									if(personnal.isPresent() )
									{
										personnal.get().setAdresse(user.getAdresse());
										personnal.get().setPrenom(user.getPrenom());
										personnal.get().setContratID(user.getContratID());
										personnal.get().setNom(user.getNom());
										personnal.get().setLieu_naissance(user.getLieu_naissance());
										personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
										personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										personnal.get().setType(user.getType());
										personnal.get().setNaissance(user.getNaissance());       			
										personnal.get().setEmail(user.getEmail());
										personnal.get().setTelephone(user.getTelephone());
										personnal.get().setCompteBancaire(user.getCompteBancaire());
										personnal.get().setRole(user.getRole());
										personnal.get().setNationalite(user.getNationalite());
										User userSave = personnalRepository.save(personnal.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}
								}
								else if(user.getTypeUser().equals("PARENT"))
								{
									if(parent.isPresent() )
									{
										parent.get().setAdresse(user.getAdresse());
										parent.get().setPrenom(user.getPrenom());
										parent.get().setNom(user.getNom());
										parent.get().setContratID(user.getContratID());
										parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
										parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										parent.get().setType(user.getType());
										parent.get().setNaissance(user.getNaissance());
										parent.get().setLieu_naissance(user.getLieu_naissance());
										parent.get().setEmail(user.getEmail());
										parent.get().setTelephone(user.getTelephone());
										parent.get().setCompteBancaire(user.getCompteBancaire());
										parent.get().setRole(user.getRole());
										parent.get().setNationalite(user.getNationalite());
										User userSave = parentRepository.save(parent.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");
										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}
								else if(user.getTypeUser().equals("STUDENT"))
								{
									if(student.isPresent() )
									{
										student.get().setAdresse(user.getAdresse());
										student.get().setPrenom(user.getPrenom());
										student.get().setNom(user.getNom());
										student.get().setContratID(user.getContratID());
										student.get().setNumeroMatriciule(user.getNumeroMatriciule());
										student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										student.get().setType(user.getType());
										student.get().setNaissance(user.getNaissance());   
										student.get().setNationalite(user.getNationalite());
										student.get().setEmail(user.getEmail());
										student.get().setTelephone(user.getTelephone());
										student.get().setLieu_naissance(user.getLieu_naissance());
										student.get().setCompteBancaire(user.getCompteBancaire());
										student.get().setRole(user.getRole());
										User userSave = studentRepository.save(student.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}


								}
								else
								{
									if(teacher.isPresent() )
									{
										teacher.get().setNiveauEtude(teacher.get().getNiveauEtude());
										teacher.get().setAdresse(user.getAdresse());
										teacher.get().setPrenom(user.getPrenom());
										teacher.get().setNom(user.getNom());
										teacher.get().setContratID(user.getContratID());
										teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
										teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
										teacher.get().setType(user.getType());
										teacher.get().setNaissance(user.getNaissance());
										teacher.get().setNationalite(user.getNationalite());
										teacher.get().setEmail(user.getEmail());
										teacher.get().setTelephone(user.getTelephone());
										teacher.get().setCompteBancaire(user.getCompteBancaire());
										teacher.get().setLieu_naissance(user.getLieu_naissance());
										teacher.get().setRole(user.getRole());
										User userSave = teacherRepository.save(teacher.get());
										reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
										reponse.setMessage("Ce compte a été modifié avec succès");

										reponse.setCode(200);

									}
									else
									{
										reponse.setMessage("Ce compte n'existe plus");
										reponse.setCode(201);

									}

								}		

							
					    }
					    else
					    {
					    	reponse.setCode(201);
							reponse.setMessage("Cet numéro est déjà utilisé  !");
					    }
					    
				    break;
				  case 201:
				  {
					  
					  
						if(  user.getId() != null && user.getId() != 0)
						{
							Optional<Personnal> personnal = personnalRepository.findById(user.getId());
							Optional<Student> student = studentRepository.findById(user.getId());
							Optional<Parent> parent = parentRepository.findById(user.getId());
							Optional<Teacher> teacher = teacherRepository.findById(user.getId());        	

							if(user.getTypeUser().equals("PERSONNAL"))
							{
								if(personnal.isPresent() )
								{
									personnal.get().setAdresse(user.getAdresse());
									personnal.get().setPrenom(user.getPrenom());									
									personnal.get().setContratID(user.getContratID());
									personnal.get().setNom(user.getNom());
									personnal.get().setLieu_naissance(user.getLieu_naissance());
									personnal.get().setNumeroMatriciule(user.getNumeroMatriciule());
									personnal.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									personnal.get().setType(user.getType());
									personnal.get().setNaissance(user.getNaissance());       			
									personnal.get().setEmail(user.getEmail());
									personnal.get().setTelephone(user.getTelephone());
									personnal.get().setCompteBancaire(user.getCompteBancaire());
									personnal.get().setRole(user.getRole());
									personnal.get().setNationalite(user.getNationalite());
									User userSave = personnalRepository.save(personnal.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");
									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}
							}
							else if(user.getTypeUser().equals("PARENT"))
							{
								if(parent.isPresent() )
								{
									parent.get().setAdresse(user.getAdresse());
									parent.get().setPrenom(user.getPrenom());
									parent.get().setNom(user.getNom());
									parent.get().setContratID(user.getContratID());
									parent.get().setNumeroMatriciule(user.getNumeroMatriciule());
									parent.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									parent.get().setType(user.getType());
									parent.get().setNaissance(user.getNaissance());
									parent.get().setLieu_naissance(user.getLieu_naissance());
									parent.get().setEmail(user.getEmail());
									parent.get().setTelephone(user.getTelephone());
									parent.get().setCompteBancaire(user.getCompteBancaire());
									parent.get().setRole(user.getRole());
									parent.get().setNationalite(user.getNationalite());
									User userSave = parentRepository.save(parent.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");
									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}

							}
							else if(user.getTypeUser().equals("STUDENT"))
							{
								if(student.isPresent() )
								{
									student.get().setAdresse(user.getAdresse());
									student.get().setPrenom(user.getPrenom());
									student.get().setNom(user.getNom());
									student.get().setContratID(user.getContratID());
									student.get().setNumeroMatriciule(user.getNumeroMatriciule());
									student.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									student.get().setType(user.getType());
									student.get().setNaissance(user.getNaissance());   
									student.get().setNationalite(user.getNationalite());
									student.get().setEmail(user.getEmail());
									student.get().setTelephone(user.getTelephone());
									student.get().setLieu_naissance(user.getLieu_naissance());
									student.get().setCompteBancaire(user.getCompteBancaire());
									student.get().setRole(user.getRole());
									User userSave = studentRepository.save(student.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");

									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}


							}
							else
							{
								if(teacher.isPresent() )
								{
									teacher.get().setNiveauEtude(teacher.get().getNiveauEtude());
									teacher.get().setAdresse(user.getAdresse());
									teacher.get().setPrenom(user.getPrenom());
									teacher.get().setNom(user.getNom());
									teacher.get().setContratID(user.getContratID());
									teacher.get().setNumeroMatriciule(user.getNumeroMatriciule());
									teacher.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
									teacher.get().setType(user.getType());
									teacher.get().setNaissance(user.getNaissance());
									teacher.get().setNationalite(user.getNationalite());
									teacher.get().setEmail(user.getEmail());
									teacher.get().setTelephone(user.getTelephone());
									teacher.get().setCompteBancaire(user.getCompteBancaire());
									teacher.get().setLieu_naissance(user.getLieu_naissance());
									teacher.get().setRole(user.getRole());
									User userSave = teacherRepository.save(teacher.get());
									reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
									reponse.setMessage("Ce compte a été modifié avec succès");

									reponse.setCode(200);

								}
								else
								{
									reponse.setMessage("Ce compte n'existe plus");
									reponse.setCode(201);

								}

							}			

						}
						else
						{
							
							if(user.getTypeUser().equals("PERSONNAL"))
							{

								User userSave = personnalRepository.save((Personnal)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));      			               	
								reponse.setMessage("Ce compte a été enregistré avec succès");

								reponse.setCode(200);

							}
							else if(user.getTypeUser().equals("PARENT"))
							{

								User userSave = parentRepository.save((Parent)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès");
								reponse.setCode(200);



							}
							else if(user.getTypeUser().equals("STUDENT"))
							{

								User userSave = studentRepository.save((Student)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès");
								reponse.setCode(200);




							}
							else
							{

								User userSave = teacherRepository.save((Teacher)userConverted);
								reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
								reponse.setMessage("Ce compte a été enregistré avec succès");
								reponse.setCode(200);

							}           	  


						}		  
					  
				  }
				    break;
				  default:
					  reponse=userByTelephone;
				}
		  }
		    // code block
		    break;
		  default:
			  reponse=userByEmail;
		}
		
	}
	catch (Exception e)
	{
		 reponse.setCode(500);
    	 reponse.setMessage("Une erreur interne est survenue coté serveur  !");	
	}  

	return reponse ;
}
@Override
public Reponse getUserById(Long id,String type)
{
	Reponse reponse = new Reponse();
	try
	{
		
		
		switch (type)
		{
			case Utility.STUDENT:
				Optional<Student> student = studentRepository.findById(id);
				if(student.isPresent())
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(student.get()));
					reponse.setMessage("Ce compte a été retrouvé avec succès");
				}
				else 
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(student.get()));

					reponse.setMessage("Ce compte a été retrouvé avec succès");

				}
				break;
			case Utility.PERSONNAL:
				Optional<Personnal> personnal = personnalRepository.findById(id);
				if(personnal.isPresent())
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal.get()));
					reponse.setMessage("Ce compte a été retrouvé avec succès");
				}
				else 
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal.get()));

					reponse.setMessage("Ce compte a été retrouvé avec succès");

				}

				break;
			case Utility.PARENT:
				Optional<Parent> parent = parentRepository.findById(id);
				if(parent.isPresent())
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(parent.get()));
					reponse.setMessage("Ce compte a été retrouvé avec succès");
				}
				else 
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(parent.get()));

					reponse.setMessage("Ce compte a été retrouvé avec succès");

				}


				break;
			default:
				Optional<Teacher> teacher = teacherRepository.findById(id);  
				if(teacher.isPresent())
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher.get()));
					reponse.setMessage("Ce compte a été retrouvé avec succès");
				}
				else 
				{
					reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher.get()));

					reponse.setMessage("Ce compte a été retrouvé avec succès");

				}
				break;
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
		Optional<Personnal> personnal = personnalRepository.findById(id);
		Optional<Student> student = studentRepository.findById(id);
		Optional<Parent> parent = parentRepository.findById(id);
		Optional<Teacher> teacher = teacherRepository.findById(id);      
		if(personnal.isPresent())
		{
			personnal.get().setStatus(false);
			Personnal personnalS = personnalRepository.save(personnal.get());
			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnalS));
			reponse.setMessage("Ce compte a été bloqué avec succès");
		}
		else if(student.isPresent())
		{
			student.get().setStatus(false);
			Student studentS = studentRepository.save(student.get());
			reponse.setResult(Utility.UserConvertToUserDtoResponse(studentS));
			reponse.setMessage("Ce compte a été bloqué avec succès");

		}
		else if(parent.isPresent())
		{
			parent.get().setStatus(false);
			Parent parentS = parentRepository.save(parent.get());
			reponse.setResult(Utility.UserConvertToUserDtoResponse(parentS));
			reponse.setMessage("Ce compte a été bloqué avec succès");

		}
		else if(teacher.isPresent())
		{
			teacher.get().setStatus(false);
			Teacher teacherS = teacherRepository.save(teacher.get());
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
		reponse.setMessage("Une erreur interne est survenue coté serveur");
		reponse.setCode(500);

	}

	return reponse;
}
@Override
public Reponse getUserByEmail(String email) {
	Reponse reponse = new Reponse();
	try
	{
		Optional<Personnal>  personnal = personnalRepository.findByEmail(email);
		Optional<Student>  student = studentRepository.findByEmail(email);
		Optional<Parent>  parent = parentRepository.findByEmail(email);
		Optional<Teacher>  teacher = teacherRepository.findByEmail(email);
		
		if(personnal.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal.get()));
			reponse.setMessage("Ce compte a été retrouvé avec succès");
			reponse.setCode(200);

		}
		else if(student.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(student.get()));
			reponse.setCode(200);
			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else if(parent.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(parent.get()));
			reponse.setCode(200);
			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else if(teacher.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher.get()));
			reponse.setCode(200);
			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else
		{
			reponse.setMessage("Ce compte n'existe pas");
			reponse.setResult(null);
			reponse.setCode(201);

		}


	}
	catch (Exception e) {
		reponse.setMessage("Une erreur interne est survenue");
		reponse.setCode(500);
		reponse.setResult(null);
	}

	return reponse;
}
@Override
public Reponse activation(int code) {
	Reponse response = new Reponse();	
		try
	  {
	Optional<Code> codeSave= codeRepository.findByCode(code);
	if(codeSave.isPresent() && codeSave.get().isStatus() == true )
	{			

		Reponse userGot = this.getUserById(codeSave.get().getUserID(),codeSave.get().getType());
		if(userGot.getCode() == 200)
		{					

			UserDtoResponse userConvert=null;
			if(userGot.getResult() instanceof PersonnalDtoResponse )
			{
				userConvert=(PersonnalDtoResponse) userGot.getResult();

			}
			else if(userGot.getResult() instanceof ParentDtoResponse )
			{

				userConvert=(ParentDtoResponse) userGot.getResult();


			}
			else if(userGot.getResult() instanceof StudentDtoResponse)
			{
				userConvert=(StudentDtoResponse) userGot.getResult();

			}
			else
			{
				userConvert=(TeacherDtoResponse) userGot.getResult();

			}		
			Date dateFormatter = new Date();					
			codeSave.get().setDateConnexion(dateFormatter.getTime());	
			codeSave.get().setStatus(false);
			codeRepository.save(codeSave.get()); 						
			userConvert.setMonToken(this.getToken(userConvert.getTelephone(),userConvert.getPassword()));	
			User userGotFromUserConvert=Utility.UserDtoResponseConvertToUser(userConvert);
			this.createOrUpdateUser(Utility.UserConvertToUserDtoRequest(userGotFromUserConvert)); 
			response.setCode(200);
			response.setMessage("L'activation a reussi");
			response.setResult(userConvert);											
		}
		else
		{

			response.setCode(202);
			response.setMessage("L'activation a échoué,"+userGot.getMessage());
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
		Optional<Personnal> personnal = personnalRepository.findByTelephone(telephone);
		Optional<Student>  student = studentRepository.findByTelephone(telephone);
		Optional<Parent>  parent = parentRepository.findByTelephone(telephone);
		Optional<Teacher>  teacher = teacherRepository.findByTelephone(telephone);      
		if(personnal.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(personnal.get()));
			reponse.setCode(200);

			reponse.setMessage("Ce compte a été retrouvé avec succès");
		}
		else if(student.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(student.get()));
			reponse.setCode(200);

			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else if(parent.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(parent.get()));
			reponse.setCode(200);

			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else if(teacher.isPresent())
		{
			reponse.setResult(Utility.UserConvertToUserDtoResponse(teacher.get()));
			reponse.setCode(200);

			reponse.setMessage("Ce compte a été retrouvé avec succès");

		}
		else
		{
			reponse.setMessage("Ce compte n'existe pas");
			reponse.setCode(201);
			reponse.setResult(null);

		}


	}
	catch (Exception e) {
		reponse.setMessage("Une erreur interne est survenue");
		reponse.setCode(500);
		reponse.setResult(null);
	}  

	return reponse;

}
@Override
public Reponse getAllUsersByType(String type)
{
	Reponse reponse = new Reponse();

	List<UserDtoResponse> users= null;		

	try
	{   
		switch (type)
		{
		case "PERSONNAL":
			users=personnalRepository.findAll()
			.stream()
			.map(Utility :: UserConvertToUserDtoResponse)
			.collect(Collectors.toList());;
			break;
		case "STUDENT":
			users=studentRepository.findAll()
			.stream()
			.map(Utility :: UserConvertToUserDtoResponse)
			.collect(Collectors.toList());;
			break;
		case "PARENT":
			users=parentRepository.findAll()
			.stream()
			.map(Utility :: UserConvertToUserDtoResponse)
			.collect(Collectors.toList());;
			break;
		case "TEACHER":
			users=teacherRepository.findAll()
			.stream()
			.map(Utility :: UserConvertToUserDtoResponse)
			.collect(Collectors.toList());;
			break;
		default:
			users=personnalRepository.findAll()
			.stream()
			.map(Utility :: UserConvertToUserDtoResponse)
			.collect(Collectors.toList());;
			break;
		}

		reponse.setCode(200);
		reponse.setMessage(" La liste "+type);
		reponse.setResult(users);
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
		reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}

}

