

package com.gestion_ecole.ecole.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.entities.Ass_parent_student;
import com.gestion_ecole.ecole.entities.EmailDetails;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.repository.AssParentStudentRepository;
import com.gestion_ecole.ecole.repository.ParentRepository;
import com.gestion_ecole.ecole.repository.StudentRepository;
import com.gestion_ecole.ecole.utils.Utility;


@Service
@Transactional
public class ParentService implements IParentService{
		@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private AssParentStudentRepository assParentStudentRepository;
	@Autowired
	private IEmailService emailService;
	@Override
	public Reponse createOrUpdateParent(ParentDtoRequest parent) {
		Reponse reponse = new Reponse();
		try
		{
			Optional<Parent> userByEmail = parentRepository.findByEmail(parent.getEmail());		
			Parent userConverted = (Parent) Utility.UserDtoRequestConvertToUser(parent);
			String pwdCryp = bCryptPasswordEncoder.encode(parent.getPassword());
			Reponse userExiste = accountService.CheckEmailOrPhone(parent.getTelephone(),parent.getEmail());			
			Optional<Student> student=studentRepository.findById(parent.getStudentID());
			if(userExiste.getCode() == 200)
			{
				if( parent.getId() != 0)
				{
					userByEmail.get().setAdresse(parent.getAdresse());
					userByEmail.get().setPrenom(parent.getPrenom());
					userByEmail.get().setNom(parent.getNom());
					userByEmail.get().setContratID(parent.getContratID());
					userByEmail.get().setNumeroMatriciule(parent.getNumeroMatriciule());
					userByEmail.get().setTypeDeRecrutement(parent.getTypeDeRecrutement());
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
				else
				{
					
					if(student.isPresent())
					{
						
					       String content ="Bonjour chers parent, \n\n"
					                + "Vous avez reçu cet email car vous avez un enfant inscrit à UCAD. \n"
					                + "Pour vous connectez ,utilisez les identifiants ci-dessous pour suivre l'avancement de votre enfant.\n" 
					                + "Login : "+parent.getTelephone()+" \n"
					                + "Mot de passe :  "+parent.getPassword()+" \n"
					                + "lien de connexion  : "+Utility.LINK+" \n\n"
					                + "Veuillez ne pas repondre à cet email.";     
					      					    	
						   EmailDetails emailDetails = new EmailDetails(parent.getEmail(), content,Utility.SUBJECT,Utility.ATTACHEMENT);
						   emailService.sendSimpleMail(emailDetails);				

						userConverted.setPassword(pwdCryp);
						Ass_parent_student ass_parent_student = new Ass_parent_student();
						ass_parent_student.setApprouve(false);
						ass_parent_student.setDate_enregistrement(Utility.getToday());
						ass_parent_student.setRejette(true);
						ass_parent_student.setStudentID(parent.getStudentID());
						
						Ass_parent_student ass_parent_studentSave=assParentStudentRepository.save(ass_parent_student);
						userConverted.getAss_parent_students().add(ass_parent_studentSave);
						Parent userSave = parentRepository.save(userConverted);
						student.get().setParentID(userSave.getId());											
						studentRepository.save(student.get());											
						reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
						reponse.setMessage("Ce compte a été enregistré avec succès");
						reponse.setCode(200);	
					}
					else
					{
						reponse.setMessage("Aucun élève pour ce parent");
						reponse.setCode(201);
					}
				}				
				
			}
			else if(userExiste.getCode() == 201)
			{
				
				
				if( parent.getId() != 0 && parent.getId() == userByEmail.get().getId() )
				{
					
					if(parent.getStudentID() != 0 && student.isPresent() && userByEmail.get().isMonPremiereConnexion())
					{
						
						student.get().setParentID(parent.getId());											
						studentRepository.save(student.get());		
						Ass_parent_student ass_parent_student = new Ass_parent_student();
						ass_parent_student.setApprouve(false);
						ass_parent_student.setDate_enregistrement(Utility.getToday());
						ass_parent_student.setRejette(true);
						ass_parent_student.setStudentID(parent.getStudentID());
						Ass_parent_student ass_parent_studentSave=assParentStudentRepository.save(ass_parent_student);					
						userByEmail.get().getAss_parent_students().add(ass_parent_studentSave);
						Parent userSave = parentRepository.save(userByEmail.get());
						reponse.setResult(Utility.UserConvertToUserDtoResponse(userSave));
						reponse.setMessage("Ce parent existe déjà , veuillez attendre son approbation parentale ! ");
						reponse.setCode(200);	
						
					}
					else
					{
						userByEmail.get().setAdresse(parent.getAdresse());
						userByEmail.get().setPrenom(parent.getPrenom());
						userByEmail.get().setNom(parent.getNom());
						userByEmail.get().setContratID(parent.getContratID());
						userByEmail.get().setNumeroMatriciule(parent.getNumeroMatriciule());
						userByEmail.get().setTypeDeRecrutement(parent.getTypeDeRecrutement());
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
    	 reponse.setMessage("Une erreur interne est survenue coté serveur  ! ");	
	}  

	return reponse ;
}

}

