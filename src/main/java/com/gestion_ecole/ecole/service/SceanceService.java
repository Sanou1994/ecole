package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.dto.response.SeanceDtoResponse;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.Sceance;
import com.gestion_ecole.ecole.repository.SeanceRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class SceanceService implements ISceanceService {
	@Autowired
	SeanceRepository SceanceRepository;
	
	@Override
	public Reponse createOrUpdateSceance(SeanceDtoRequest Sceance) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Sceance> soft = this.SceanceRepository.findByType(Sceance.getType());
			if(!soft.isPresent())
			{
				
				if(Sceance.getId() != null)
				{
					Optional<Sceance> softGot = this.SceanceRepository.findById(Sceance.getId());

					if(softGot.isPresent())
					{
						softGot.get().setType(Sceance.getType());
						softGot.get().setCoefficient(Sceance.getCoefficient());
						softGot.get().setMontantHoraire(Sceance.getMontantHoraire());
						softGot.get().setNombreHeure(Sceance.getNombreHeure());
						SceanceRepository.save(softGot.get());
				    	reponse.setMessage(" La Sceance a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" La Sceance n'existe plus !");
					}
					
				}
				else
				{
					SceanceRepository.save(Utility.toSceance(Sceance));
					reponse.setCode(200);
			    	reponse.setMessage(" La Sceance a été créée avec succès !");
			    	reponse.setResult(Sceance);
				}
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" La Sceance existe déjà!");
		    	reponse.setResult(Sceance);
		    	
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
			Optional<Sceance> soft = this.SceanceRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La séance a été obtenue avec succès!");
	    	reponse.setResult(Utility.toSceanceDtoResponse(soft.orElse(null)));
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
			Sceance sceance = SceanceRepository.findById(id).get();
		    if(sceance != null)
		    {
		    	sceance.setStatus(false);
		    	SceanceRepository.save(sceance);
		    	reponse.setCode(200);
		    	reponse.setMessage(" La séance a été supprimée avec succès");
		    	reponse.setResult(Utility.toSceanceDtoResponse(sceance));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette Sceance n'existe pas ");
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
		{   List<SeanceDtoResponse> Sceances= SceanceRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toSceanceDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  scéances a été obtenue avec succès");
	    	reponse.setResult(Sceances);
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

}
