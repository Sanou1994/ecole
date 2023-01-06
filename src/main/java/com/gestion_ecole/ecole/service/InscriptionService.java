package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.InscriptionDtoRequest;
import com.gestion_ecole.ecole.dto.response.InscriptionDtoResponse;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.InscriptionRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class InscriptionService implements IInscriptionService {
	@Autowired
	InscriptionRepository inscriptionRepository;

	public InscriptionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reponse createOrUpdateInscription(InscriptionDtoRequest inscription) {
		Reponse reponse = new Reponse();	

		try
		{  
				if(inscription.getId() != null)
				{
					Optional<Inscription> softGot = this.inscriptionRepository.findById(inscription.getId());

					if(softGot.isPresent())
					{
						
						inscriptionRepository.save(softGot.get());
				    	reponse.setMessage(" L'inscription a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" L'inscription n'existe plus !");
					}
					
				}
				else
				{
					inscriptionRepository.save(Utility.toInscription(inscription));
					reponse.setCode(200);
			    	reponse.setMessage(" L'inscription a été créée avec succès !");
			    	reponse.setResult(inscription);
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
	public Reponse getInscriptionById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Inscription> soft = this.inscriptionRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La classe  a été obtenue avec succès!");
	    	reponse.setResult(Utility.toInscriptionDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

	@Override
	public Reponse deleteInscription(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Inscription inscription = inscriptionRepository.findById(id).get();
		    if(inscription != null)
		    {
                inscriptionRepository.delete(inscription);
		    	reponse.setCode(200);
		    	reponse.setMessage(" L'inscription a été supprimée avec succès");
		    	reponse.setResult(Utility.toInscriptionDtoResponse(inscription));
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
	public Reponse listeInscriptions() {
		Reponse reponse = new Reponse();	

		try
		{   List<InscriptionDtoResponse> inscriptions= inscriptionRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toInscriptionDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  inscriptions a été obtenu avec succès");
	    	reponse.setResult(inscriptions);
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
