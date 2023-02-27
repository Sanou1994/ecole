package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.PosteDtoRequest;
import com.gestion_ecole.ecole.dto.response.PosteDtoResponse;
import com.gestion_ecole.ecole.entities.Poste;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.PosteRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class PosteService implements IPosteService{
	@Autowired
	private PosteRepository posteRepository;
	


	@Override
	public Reponse createOrUpdatePoste(PosteDtoRequest poste) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Poste> soft = this.posteRepository.findByNom(poste.getNom());
			if(!soft.isPresent())
			{
				
				if(poste.getId() != 0)
				{
					Optional<Poste> softGot = this.posteRepository.findById(poste.getId());

					if(softGot.isPresent())
					{
						softGot.get().setNom(poste.getNom());
						posteRepository.save(softGot.get());
				    	reponse.setMessage(" La Poste a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" La Poste n'existe plus !");
					}
					
				}
				else
				{
					posteRepository.save(Utility.toEntityPosteFromRequest(poste));
					reponse.setCode(200);
			    	reponse.setMessage(" Le Poste a été créée avec succès !");
			    	reponse.setResult(poste);
				}
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" La Poste existe déjà!");
		    	reponse.setResult(poste);
		    	
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
	public Reponse getPosteById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Poste> soft = this.posteRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La Poste  a été obtenue avec succès!");
	    	reponse.setResult(Utility.toDtoPosteDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}


	@Override
	public Reponse bloquerPoste(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Poste poste = posteRepository.findById(id).get();
		    if(poste != null)
		    {
                 poste.setStatus(false);
		    Poste cs=	posteRepository.save(poste);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La Poste a été supprimée avec succès");
		    	reponse.setResult(Utility.toDtoPosteDtoResponse(cs));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette Poste n'existe pas ");
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
	public Reponse ListePostes() {
		Reponse reponse = new Reponse();	

		try
		{   List<PosteDtoResponse> Postes= posteRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toDtoPosteDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  Postes a été obtenu avec succès");
	    	reponse.setResult(Postes);
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
