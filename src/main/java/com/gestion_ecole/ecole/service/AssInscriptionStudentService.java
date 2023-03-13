package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.Ass_prinscription_studentDtoRequest;
import com.gestion_ecole.ecole.dto.response.Ass_prinscription_studentDtoResponse;
import com.gestion_ecole.ecole.entities.Ass_prinscription_student;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.AssInscriptionStudentRepository;
import com.gestion_ecole.ecole.repository.DepartementRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class AssInscriptionStudentService   implements IAssInscriptionStudentService{
@Autowired
DepartementRepository departementRepository;
@Autowired
AssInscriptionStudentRepository assParentStudentRepository;
@Override
public Reponse createOrUpdateAssInscriptionStudent(Ass_prinscription_studentDtoRequest ass_prinscription_student) {
	Reponse reponse = new Reponse();	

	try
	{   

				assParentStudentRepository.save(Utility.toEntityAss_prinscription_studentFromRequest(ass_prinscription_student));
				reponse.setCode(200);
		    	reponse.setResult(ass_prinscription_student);
		
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	} 
	return reponse ;

}

@Override
public Reponse getAssInscriptionStudentById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Ass_prinscription_student> soft =assParentStudentRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" Le département a été obtenu avec succès!");
    	reponse.setResult(Utility.toDtoAss_prinscription_studentDtoResponse(soft.orElse(null)));
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}





@Override
public Reponse bloquerAssInscriptionStudent(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		Ass_prinscription_student ass_prinscription_student = assParentStudentRepository.findById(id).get();
	    if(ass_prinscription_student != null)
	    {
	    	ass_prinscription_student.setStatus(false);
	    	assParentStudentRepository.save(ass_prinscription_student);
	    	reponse.setCode(200);
	    	reponse.setMessage(" Le L'associassion a été bloquée avec succès");
	    	reponse.setResult(Utility.toDtoAss_prinscription_studentDtoResponse(ass_prinscription_student));
	    }	
	    else
	    {
	    	reponse.setCode(201);
	    	reponse.setMessage(" Ce département n'existe pas ");
	    }
		return reponse ;

	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
		return reponse ;

	} 
}

@Override
public Reponse ListeAssInscriptionStudents(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   List<Ass_prinscription_studentDtoResponse> ass_prinscription_students= assParentStudentRepository.findByStructureID(id)
	                                                      .stream()
	                                                      .map(Utility :: toDtoAss_prinscription_studentDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
    	reponse.setResult(ass_prinscription_students);
    	return reponse ;
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
		return reponse ;
	}
}



}
