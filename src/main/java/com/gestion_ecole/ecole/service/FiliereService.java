package com.gestion_ecole.ecole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.FiliereRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class FiliereService {
@Autowired
FiliereRepository filiereRepository;

public Reponse createFiliere(FiliereDtoRequest filiere) 
{
	
	Reponse reponse = new Reponse();	

	try
	{   
		
			filiereRepository.save(Utility.toFiliere(filiere));
			reponse.setCode(200);
	    	reponse.setMessage(" La filière a été créée avec succès!");
	    	reponse.setResult(filiere);
	    	return reponse ;
		
		
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
		Optional<Filiere> soft = this.filiereRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" La filière a été obtenue avec succès!");
    	reponse.setResult(Utility.toFiliereDtoResponse(soft.orElse(null)));
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
		Filiere filiere = filiereRepository.findById(id).get();
	    if(filiere != null)
	    {
	    	filiereRepository.deleteById(id);
	    	reponse.setCode(200);
	    	reponse.setMessage(" La filière a été supprimée avec succès");
	    	reponse.setResult(Utility.toFiliereDtoResponse(filiere));
	    }	
	    else
	    {
	    	reponse.setCode(201);
	    	reponse.setMessage(" Cette filière n'existe pas ");
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
