package com.gestion_ecole.ecole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.repository.SeanceRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class SeanceService {
	@Autowired
	SeanceRepository seanceRepository;
	
	public Reponse createSeance(SeanceDtoRequest seance) 
	{
		
		Reponse reponse = new Reponse();	

		try
		{   
			
				seanceRepository.save(Utility.toSeance(seance));
				reponse.setCode(200);
		    	reponse.setMessage(" La seance a été créée avec succès!");
		    	reponse.setResult(seance);
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
			Optional<Seance> soft = this.seanceRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La séance a été obtenue avec succès!");
	    	reponse.setResult(Utility.toSeanceDtoResponse(soft.orElse(null)));
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
			Seance seance = seanceRepository.findById(id).get();
		    if(seance != null)
		    {
		    	seanceRepository.deleteById(id);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La séance a été supprimée avec succès");
		    	reponse.setResult(Utility.toSeanceDtoResponse(seance));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette seance n'existe pas ");
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
