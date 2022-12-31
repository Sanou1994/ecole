package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.dto.response.SeanceDtoResponse;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.repository.SeanceRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class SeanceService implements ISceanceService {
	@Autowired
	SeanceRepository seanceRepository;
	
	@Override
	public Reponse createOrUpdateSceance(SeanceDtoRequest seance) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Seance> soft = this.seanceRepository.findByType(seance.getType());
			if(!soft.isPresent())
			{
				
				if(seance.getId() != null)
				{
					Optional<Seance> softGot = this.seanceRepository.findById(seance.getId());

					if(softGot.isPresent())
					{
						softGot.get().setType(seance.getType());
						softGot.get().setCoefficient(seance.getCoefficient());
						softGot.get().setMontantHoraire(seance.getMontantHoraire());
						softGot.get().setNombreHeure(seance.getNombreHeure());
						seanceRepository.save(softGot.get());
				    	reponse.setMessage(" La seance a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" La seance n'existe plus !");
					}
					
				}
				else
				{
					seanceRepository.save(Utility.toSeance(seance));
					reponse.setCode(200);
			    	reponse.setMessage(" La seance a été créée avec succès !");
			    	reponse.setResult(seance);
				}
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" La seance existe déjà!");
		    	reponse.setResult(seance);
		    	
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
	public Reponse getSceanceById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Seance> soft = this.seanceRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La séance a été obtenue avec succès!");
	    	reponse.setResult(Utility.toSeanceDtoResponse(soft.orElse(null)));
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

	@Override
	public Reponse bloquerSceance(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Seance seance = seanceRepository.findById(id).get();
		    if(seance != null)
		    {
		    	seance.setStatus(false);
		    	seanceRepository.save(seance);
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

	@Override
	public Reponse ListeSceances() {
		Reponse reponse = new Reponse();	

		try
		{   List<SeanceDtoResponse> seances= seanceRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toSeanceDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  scéances a été obtenue avec succès");
	    	reponse.setResult(seances);
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

}
