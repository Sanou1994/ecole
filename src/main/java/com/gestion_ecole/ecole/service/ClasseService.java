package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.response.ClasseDtoResponse;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.ClasseRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class ClasseService implements IClasseService{
	@Autowired
	private ClasseRepository classeRepository;
	


	@Override
	public Reponse createOrUpdateClasse(ClasseDtoRequest classe) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Classe> soft = this.classeRepository.findByNom(classe.getNom());
			if(!soft.isPresent())
			{
				
				if(classe.getId() != null)
				{
					Optional<Classe> softGot = this.classeRepository.findById(classe.getId());

					if(softGot.isPresent())
					{
						softGot.get().setNom(classe.getNom());
						classeRepository.save(softGot.get());
				    	reponse.setMessage(" La classe a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" La classe n'existe plus !");
					}
					
				}
				else
				{
					classeRepository.save(Utility.toClasse(classe));
					reponse.setCode(200);
			    	reponse.setMessage(" La classe a été créée avec succès !");
			    	reponse.setResult(classe);
				}
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" La classe existe déjà!");
		    	reponse.setResult(classe);
		    	
			}
			
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
			
		}
		return reponse ;
	}


	@Override
	public Reponse getClasseById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Classe> soft = this.classeRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La classe  a été obtenue avec succès!");
	    	reponse.setResult(Utility.toClasseDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}


	@Override
	public Reponse bloquerClasse(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Classe classe = classeRepository.findById(id).get();
		    if(classe != null)
		    {
                 classe.setStatus(false);
		    Classe cs=	classeRepository.save(classe);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La classe a été supprimée avec succès");
		    	reponse.setResult(Utility.toClasseDtoResponse(cs));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette classe n'existe pas ");
		    }

		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
			

		}  
		return reponse ;
	}


	@Override
	public Reponse ListeClasses() {
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


}
