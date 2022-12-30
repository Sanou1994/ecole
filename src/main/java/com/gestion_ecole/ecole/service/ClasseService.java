package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.dto.response.ClasseDtoResponse;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.ClasseRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class ClasseService {
	@Autowired
	ClasseRepository classeRepository;
	
	public Reponse createClasse(ClasseDtoRequest classe) 
	{
		
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Classe> soft = this.classeRepository.findByNom(classe.getNom());
			if(soft==null) {
				classeRepository.save(Utility.toClasse(classe));
				reponse.setCode(200);
		    	reponse.setMessage(" La classe a été créée avec succès!");
		    	reponse.setResult(classe);
		    	return reponse ;
			}
			else {
				reponse.setCode(201);
		    	reponse.setMessage(" La classe existe déjà!");
		    	reponse.setResult(classe);
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
	
	
    public Reponse findAllClasses()
    {
    	Reponse reponse = new Reponse();	

		try
		{   List<ClasseDtoResponse> classes= classeRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toClasseDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  classes a été obtenu avec succès");
	    	reponse.setResult(classes);
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
			Optional<Classe> soft = this.classeRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" Le département a été obtenu avec succès!");
	    	reponse.setResult(Utility.toClasseDtoResponse(soft.orElse(null)));
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
			Classe classe = classeRepository.findById(id).get();
		    if(classe != null)
		    {
		    	classeRepository.deleteById(id);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La classe a été supprimée avec succès");
		    	reponse.setResult(Utility.toClasseDtoResponse(classe));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette classe n'existe pas ");
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
