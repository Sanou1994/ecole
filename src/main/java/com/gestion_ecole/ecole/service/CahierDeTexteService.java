package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestion_ecole.ecole.dto.request.CahierDeTexteDtoRequest;
import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.dto.response.CahierDeTexteDtoResponse;
import com.gestion_ecole.ecole.dto.response.EmploiDuTempsDtoResponse;
import com.gestion_ecole.ecole.entities.CahierDeTexte;
import com.gestion_ecole.ecole.entities.EmploiDuTemps;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.CahierDeTexteRepository;
import com.gestion_ecole.ecole.utils.Utility;

public class CahierDeTexteService implements ICahierDeTexteService {
	@Autowired
	CahierDeTexteRepository cahierDeTexteRepository;

	public CahierDeTexteService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Reponse createOrUpdateCahierDeTexte(CahierDeTexteDtoRequest cahierDeTexte) {
		Reponse reponse = new Reponse();	

		try
		{  
				if(cahierDeTexte.getId() != null)
				{
					Optional<CahierDeTexte> softGot = this.cahierDeTexteRepository.findById(cahierDeTexte.getId());

					if(softGot.isPresent())
					{
						
						cahierDeTexteRepository.save(softGot.get());
				    	reponse.setMessage(" Le cahier de texte a été modifié avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" Le cahier de texte n'existe plus !");
					}
					
				}
				else
				{
					cahierDeTexteRepository.save(Utility.toCahierDeTexte(cahierDeTexte));
					reponse.setCode(200);
			    	reponse.setMessage(" Le cahier de texte a été créé avec succès !");
			    	reponse.setResult(cahierDeTexte);
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
	public Reponse getCahierDeTexteById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<CahierDeTexte> soft = this.cahierDeTexteRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" Le cahier de texte  a été obtenu avec succès!");
	    	reponse.setResult(Utility.toCahierDeTexteDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

	@Override
	public Reponse deleteCahierDeTexte(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			CahierDeTexte cahierDeTexte = cahierDeTexteRepository.findById(id).get();
		    if(cahierDeTexte != null)
		    {
		    	cahierDeTexteRepository.delete(cahierDeTexte);
		    	reponse.setCode(200);
		    	reponse.setMessage(" Le cahier de texte a été supprimé avec succès");
		    	reponse.setResult(Utility.toCahierDeTexteDtoResponse(cahierDeTexte));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Ce cahier de texte n'existe pas ");
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
	public Reponse listeCahierDeTexte() {
		Reponse reponse = new Reponse();	

		try
		{   List<CahierDeTexteDtoResponse> cahierDeTexte= cahierDeTexteRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toCahierDeTexteDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  cahiers de texte a été obtenu avec succès");
	    	reponse.setResult(cahierDeTexte);
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
