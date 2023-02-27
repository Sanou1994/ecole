package com.gestion_ecole.ecole.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.ParentRepository;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class ParentService implements IParentService{
		@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ParentRepository parentRepository; 	
	@Override
	public Reponse createOrUpdateParent(ParentDtoRequest parent) {
		Reponse reponse = new Reponse();
		try
		{

		Optional<Parent> userByEmail = parentRepository.findByEmail(parent.getEmail());		
		Parent userConverted = (Parent) Utility.UserDtoRequestConvertToUser(parent);
		String pwdCryp = bCryptPasswordEncoder.encode(parent.getPassword());
				
		if(userByEmail.isPresent() )
		{
			if( parent.getId() != 0 && parent.getId() == userByEmail.get().getId() )
			{
				userByEmail.get().setAdresse(parent.getAdresse());
				userByEmail.get().setPrenom(parent.getPrenom());
				userByEmail.get().setNom(parent.getNom());
				userByEmail.get().setContratID(parent.getContratID());
				userByEmail.get().setNumeroMatriciule(parent.getNumeroMatriciule());
				userByEmail.get().setTypeDeRecrutement(parent.getTypeDeRecrutement());
				userByEmail.get().setType(parent.getType());
				userByEmail.get().setNaissance(parent.getNaissance());
				userByEmail.get().setLieu_naissance(parent.getLieu_naissance());
				userByEmail.get().setEmail(parent.getEmail());
				userByEmail.get().setTelephone(parent.getTelephone());
				userByEmail.get().setCompteBancaire(parent.getCompteBancaire());
				userByEmail.get().setRole(parent.getRole());
				userByEmail.get().setNationalite(parent.getNationalite());
				Parent userSave = parentRepository.save(userByEmail.get());
				reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
				reponse.setMessage("Ce compte a été modifié avec succès");
				reponse.setCode(200);
				
			}
			else if( parent.getId() == 0 && parent.getId() != userByEmail.get().getId())
			{
				reponse.setMessage("Cet email existe déjà !");
				reponse.setCode(201);	
			}
			else
			{
				userConverted.setPassword(pwdCryp);
				Parent userSave = parentRepository.save(userConverted);
				reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
				reponse.setMessage("Ce compte a été enregistré avec succès");
				reponse.setCode(200);	
				
			}
			
			

		}
		else
		{
			userConverted.setPassword(pwdCryp);
			Parent userSave = parentRepository.save(userConverted);
			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
			reponse.setMessage("Ce compte a été enregistré avec succès");
			reponse.setCode(200);	
			

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

