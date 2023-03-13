package com.gestion_ecole.ecole.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.entities.Personnal;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.User;
import com.gestion_ecole.ecole.repository.PersonnalRepository;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class PersonnalService implements IPersonnalService{
		@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
		@Autowired
		private IAccountService accountService;
	@Autowired
	private PersonnalRepository personnalRepository; 	
	@Override
	public Reponse createOrUpdatePersonnal(PersonnalDtoRequest personnal) {		
		Reponse reponse = new Reponse();
		try
		{
			Optional<Personnal> userByEmail = personnalRepository.findByEmail(personnal.getEmail());		
			Personnal userConverted = (Personnal) Utility.UserDtoRequestConvertToUser(personnal);
			String pwdCryp = bCryptPasswordEncoder.encode(personnal.getPassword());
			Reponse userExiste = accountService.CheckEmailOrPhone(personnal.getTelephone(),personnal.getEmail());			

			if(userExiste.getCode() == 200)
			{
				if( personnal.getId() != 0)
				{
					userByEmail.get().setAdresse(personnal.getAdresse());
					userByEmail.get().setPrenom(personnal.getPrenom());
					userByEmail.get().setNom(personnal.getNom());
					userByEmail.get().setLieu_naissance(personnal.getLieu_naissance());
					userByEmail.get().setNumeroMatriciule(personnal.getNumeroMatriciule());
					userByEmail.get().setTypeDeRecrutement(personnal.getTypeDeRecrutement());
					userByEmail.get().setContratID(personnal.getContratID());
					userByEmail.get().setType(personnal.getType());
					userByEmail.get().setNaissance(personnal.getNaissance());       			
					userByEmail.get().setEmail(personnal.getEmail());
					userByEmail.get().setTelephone(personnal.getTelephone());
					userByEmail.get().setCompteBancaire(personnal.getCompteBancaire());
					userByEmail.get().setRole(personnal.getRole());
					userByEmail.get().setNationalite(personnal.getNationalite());
					User userSave = personnalRepository.save(userByEmail.get());
					reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
					reponse.setMessage("Ce compte a été modifié avec succès");
					reponse.setCode(200);
					
				}				
				else
				{
					userConverted.setPassword(pwdCryp);
					Personnal userSave = personnalRepository.save(userConverted);
					reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
					reponse.setMessage("Ce compte a été enregistré avec succès");
					reponse.setCode(200);	
					
				}				
				
			}
			else if(userExiste.getCode() == 201)
			{
				
				
				if( personnal.getId() != 0 && personnal.getId() == userByEmail.get().getId() )
				{
					userByEmail.get().setAdresse(personnal.getAdresse());
					userByEmail.get().setPrenom(personnal.getPrenom());
					userByEmail.get().setNom(personnal.getNom());
					userByEmail.get().setLieu_naissance(personnal.getLieu_naissance());
					userByEmail.get().setNumeroMatriciule(personnal.getNumeroMatriciule());
					userByEmail.get().setTypeDeRecrutement(personnal.getTypeDeRecrutement());
					userByEmail.get().setContratID(personnal.getContratID());
					userByEmail.get().setType(personnal.getType());
					userByEmail.get().setNaissance(personnal.getNaissance());       			
					userByEmail.get().setEmail(personnal.getEmail());
					userByEmail.get().setTelephone(personnal.getTelephone());
					userByEmail.get().setCompteBancaire(personnal.getCompteBancaire());
					userByEmail.get().setRole(personnal.getRole());
					userByEmail.get().setNationalite(personnal.getNationalite());
					User userSave = personnalRepository.save(userByEmail.get());
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

