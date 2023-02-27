package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.ContratDtoRequest;
import com.gestion_ecole.ecole.dto.response.ContratDtoResponse;
import com.gestion_ecole.ecole.entities.Contrat;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.ContratRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class ContratService implements IContratService{
	@Autowired
	private ContratRepository contratRepository;
	


	@Override
	public Reponse createOrUpdateContrat(ContratDtoRequest contrat) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Contrat> soft = this.contratRepository.findByType(contrat.getType());
			if(!soft.isPresent())
			{
				
				if(contrat.getId() != 0)
				{
					Optional<Contrat> softGot = this.contratRepository.findById(contrat.getId());

					if(softGot.isPresent())
					{
						softGot.get().setType(contrat.getType());
						softGot.get().setMontant(contrat.getMontant());
						softGot.get().setDuree(contrat.getDuree());
						contratRepository.save(softGot.get());
				    	reponse.setMessage(" La Contrat a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" La Contrat n'existe plus !");
					}
					
				}
				else
				{
					contratRepository.save(Utility.toEntityContratFromRequest(contrat));
					reponse.setCode(200);
			    	reponse.setMessage(" La Contrat a été créée avec succès !");
			    	reponse.setResult(contrat);
				}
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" La Contrat existe déjà!");
		    	reponse.setResult(contrat);
		    	
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
	public Reponse getContratById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Contrat> soft = this.contratRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La Contrat  a été obtenue avec succès!");
	    	reponse.setResult(Utility.toDtoContratDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}


	@Override
	public Reponse bloquerContrat(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Contrat Contrat = contratRepository.findById(id).get();
		    if(Contrat != null)
		    {
                 Contrat.setStatus(false);
		    Contrat cs=	contratRepository.save(Contrat);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La Contrat a été supprimée avec succès");
		    	reponse.setResult(Utility.toDtoContratDtoResponse(cs));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette Contrat n'existe pas ");
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
	public Reponse ListeContrats() {
		Reponse reponse = new Reponse();	

		try
		{   List<ContratDtoResponse> Contrats= contratRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toDtoContratDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  Contrats a été obtenu avec succès");
	    	reponse.setResult(Contrats);
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
