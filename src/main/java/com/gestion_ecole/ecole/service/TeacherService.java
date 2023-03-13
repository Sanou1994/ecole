package com.gestion_ecole.ecole.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Teacher;
import com.gestion_ecole.ecole.repository.TeacherRepository;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class TeacherService implements ITeacherService{
		@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private TeacherRepository teacherRepository; 
	@Autowired
	private IAccountService accountService;
	@Override
	public Reponse createOrUpdateTeacher(TeacherDtoRequest user) {		
		Reponse reponse = new Reponse();
		try
		{
			Optional<Teacher> userByEmail = teacherRepository.findByEmail(user.getEmail());		
			Teacher userConverted = (Teacher) Utility.UserDtoRequestConvertToUser(user);
			String pwdCryp = bCryptPasswordEncoder.encode(user.getPassword());
			Reponse userExiste = accountService.CheckEmailOrPhone(user.getTelephone(),user.getEmail());			

			if(userExiste.getCode() == 200)
			{
				if( user.getId() != 0)
				{
					userByEmail.get().setNiveauEtude(user.getNiveauEtude());
					userByEmail.get().setAdresse(user.getAdresse());
					userByEmail.get().setPrenom(user.getPrenom());
					userByEmail.get().setNom(user.getNom());
					userByEmail.get().setContratID(user.getContratID());
					userByEmail.get().setNumeroMatriciule(user.getNumeroMatriciule());
					userByEmail.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
					userByEmail.get().setType(user.getType());
					userByEmail.get().setNaissance(user.getNaissance());
					userByEmail.get().setNationalite(user.getNationalite());
					userByEmail.get().setEmail(user.getEmail());
					userByEmail.get().setTelephone(user.getTelephone());
					userByEmail.get().setCompteBancaire(user.getCompteBancaire());
					userByEmail.get().setLieu_naissance(user.getLieu_naissance());
					userByEmail.get().setRole(user.getRole());
					Teacher userSave = teacherRepository.save(userByEmail.get());
					reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
					reponse.setMessage("Ce compte a été modifié avec succès");

					reponse.setCode(200);
				}				
				else
				{
					userConverted.setPassword(pwdCryp);
					Teacher userSave = teacherRepository.save(userConverted);
					reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
					reponse.setMessage("Ce compte a été enregistré avec succès");
					reponse.setCode(200);	
					
				}				
				
			}
			else if(userExiste.getCode() == 201)
			{
				
				
				if( user.getId() != 0 && user.getId() == userByEmail.get().getId() )
				{
					userByEmail.get().setNiveauEtude(user.getNiveauEtude());
					userByEmail.get().setAdresse(user.getAdresse());
					userByEmail.get().setPrenom(user.getPrenom());
					userByEmail.get().setNom(user.getNom());
					userByEmail.get().setContratID(user.getContratID());
					userByEmail.get().setNumeroMatriciule(user.getNumeroMatriciule());
					userByEmail.get().setTypeDeRecrutement(user.getTypeDeRecrutement());
					userByEmail.get().setType(user.getType());
					userByEmail.get().setNaissance(user.getNaissance());
					userByEmail.get().setNationalite(user.getNationalite());
					userByEmail.get().setEmail(user.getEmail());
					userByEmail.get().setTelephone(user.getTelephone());
					userByEmail.get().setCompteBancaire(user.getCompteBancaire());
					userByEmail.get().setLieu_naissance(user.getLieu_naissance());
					userByEmail.get().setRole(user.getRole());
					Teacher userSave = teacherRepository.save(userByEmail.get());
					reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
					reponse.setMessage("Ce compte a été modifié avec succès");

					reponse.setCode(200);
					
				}				
				else
				{					
					reponse.setMessage(userExiste.getMessage());
					reponse.setCode(userExiste.getCode());	
					
				}				
			}
			else
			{
				
				reponse.setMessage(userExiste.getMessage());
				reponse.setCode(userExiste.getCode());					
			}
			
		
		
	}
	catch (Exception e)
	{
		 reponse.setCode(500);
    	 reponse.setMessage("Une erreur interne est survenue coté serveur  !");	
	}  

	return reponse ;
}
}

