package com.gestion_ecole.ecole.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.DepartementRepository;
import com.gestion_ecole.ecole.repository.FiliereRepository;
import com.gestion_ecole.ecole.repository.PaiementRepository;
import com.gestion_ecole.ecole.repository.StudentRepository;
import com.gestion_ecole.ecole.repository.TeacherRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class DepartementService {
@Autowired
DepartementRepository departementRepository;
@Autowired
FiliereRepository filiereRepository;
@Autowired
StudentRepository studentRepository;
@Autowired
TeacherRepository teacherRepository;
@Autowired
PaiementRepository paiemenentRepository;

public Reponse createDepartement(DepartementDtoRequest departement) 
{
	
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Departement> soft = this.departementRepository.findByNom(departement.getNom());
		if(soft==null) {
			departementRepository.save(Utility.toEntityDepartementFromRequest(departement));
			reponse.setCode(200);
	    	reponse.setMessage(" Le département a été créé avec succès!");
	    	reponse.setResult(departement);
	    	return reponse ;
		}
		else {
			reponse.setCode(201);
	    	reponse.setMessage(" Le département existe déjà!");
	    	reponse.setResult(departement);
	    	return reponse ;
		}
		
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
		return reponse ;
	}
	
  }


public Reponse findById(Long id) 
{
	
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Departement> soft = this.departementRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" Le département a été obtenu avec succès!");
    	reponse.setResult(Utility.toDtoDepartementDtoResponse(soft.orElse(null)));
    	return reponse ;
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
		return reponse ;
	}
	
  }



public Reponse delete(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		Departement departement = departementRepository.findById(id).get();
	    if(departement != null)
	    {
	    	departementRepository.deleteById(id);
	    	reponse.setCode(200);
	    	reponse.setMessage(" Le département a été supprimé avec succès");
	    	reponse.setResult(Utility.toDtoDepartementDtoResponse(departement));
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



}
