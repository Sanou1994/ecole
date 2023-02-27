package com.gestion_ecole.ecole.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.User;
import com.gestion_ecole.ecole.repository.StudentRepository;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class StudentService implements IStudentService{
		@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private StudentRepository studentRepository; 	
	@Override
	public Reponse createOrUpdateStudent(StudentDtoRequest student) {
		Reponse reponse = new Reponse();
		try
		{

		Optional<Student> userByEmail = studentRepository.findByEmail(student.getEmail());		
		Student userConverted = (Student) Utility.UserDtoRequestConvertToUser(student);
		String pwdCryp = bCryptPasswordEncoder.encode(student.getPassword());
				
		if(userByEmail.isPresent() )
		{
			if(student.getId() != 0 && student.getId() == userByEmail.get().getId() )
			{
				userByEmail.get().setDepartementID(student.getDepartementID());
				userByEmail.get().setFiliereID(student.getFiliereID());
				userByEmail.get().setNiveauEtudeID(student.getNiveauEtudeID());
				userByEmail.get().setAdresse(student.getAdresse());
				userByEmail.get().setPrenom(student.getPrenom());
				userByEmail.get().setNom(student.getNom());
				userByEmail.get().setLieu_naissance(student.getLieu_naissance());
				userByEmail.get().setNumeroMatriciule(student.getNumeroMatriciule());
				userByEmail.get().setTypeDeRecrutement(student.getTypeDeRecrutement());
				userByEmail.get().setType(student.getType());
				userByEmail.get().setNaissance(student.getNaissance());       			
				userByEmail.get().setEmail(student.getEmail());
				userByEmail.get().setTelephone(student.getTelephone());
				userByEmail.get().setRole(student.getRole());
				userByEmail.get().setNationalite(student.getNationalite());
				User userSave = studentRepository.save(userByEmail.get());
				reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
				reponse.setMessage("Ce compte a été modifié avec succès");
				reponse.setCode(200);
				
			}
			else if( student.getId() == 0 && student.getId() != userByEmail.get().getId())
			{
				reponse.setMessage("Cet email existe déjà !");
				reponse.setCode(201);	
			}
			else
			{
				userConverted.setPassword(pwdCryp);
				Student us = studentRepository.save(userConverted);				
				us.setNumeroMatriciule(Utility.genererMatricule(us.getId(),us.getStructureID()));
				Student userSave = studentRepository.save(us);
				
					
				reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
				reponse.setMessage("Ce compte a été enregistré avec succès");
				reponse.setCode(200);	
				
			}
			
			

		}
		else
		{
			userConverted.setPassword(pwdCryp);
			Student userSave = studentRepository.save(userConverted);			
			userSave.setNumeroMatriciule(Utility.genererMatricule(userSave.getId(), userSave.getStructureID()));
			Student userSaveM =studentRepository.save(userSave);
			reponse.setResult(Utility.UserConvertToUserDtoResponse(userSaveM));
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

