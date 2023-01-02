package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.dto.response.AbsenceDtoResponse;
import com.gestion_ecole.ecole.dto.response.EmploiDuTempsDtoResponse;
import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.EmploiDuTemps;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.EmploiDuTempsRepository;
import com.gestion_ecole.ecole.utils.Utility;

public class EmploiDuTempsService implements IEmploiDuTempsService {
	@Autowired
	EmploiDuTempsRepository emploiDuTempsRepository;

	public EmploiDuTempsService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Reponse createOrUpdateEmploiDuTemps(EmploiDuTempsDtoRequest emploiDuTemps) {
		Reponse reponse = new Reponse();	

		try
		{  
				if(emploiDuTemps.getId() != null)
				{
					Optional<EmploiDuTemps> softGot = this.emploiDuTempsRepository.findById(emploiDuTemps.getId());

					if(softGot.isPresent())
					{
						
						emploiDuTempsRepository.save(softGot.get());
				    	reponse.setMessage(" L'emploi du temps a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" L'emploi du temps n'existe plus !");
					}
					
				}
				else
				{
					emploiDuTempsRepository.save(Utility.toEmploiDuTemps(emploiDuTemps));
					reponse.setCode(200);
			    	reponse.setMessage(" L'emploi du temps a été créée avec succès !");
			    	reponse.setResult(emploiDuTemps);
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
	public Reponse getEmploiDuTempsById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<EmploiDuTemps> soft = this.emploiDuTempsRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" L'emploi du temps  a été obtenu avec succès!");
	    	reponse.setResult(Utility.toEmploiDuTempsDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

	@Override
	public Reponse deleteEmploiDuTemps(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.findById(id).get();
		    if(emploiDuTemps != null)
		    {
		    	emploiDuTempsRepository.delete(emploiDuTemps);
		    	reponse.setCode(200);
		    	reponse.setMessage(" L'emploi du temps a été supprimé avec succès");
		    	reponse.setResult(Utility.toEmploiDuTempsDtoResponse(emploiDuTemps));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cet emploi du temps n'existe pas ");
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
	public Reponse listeEmploiDuTemps() {
		Reponse reponse = new Reponse();	

		try
		{   List<EmploiDuTempsDtoResponse> absences= emploiDuTempsRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toEmploiDuTempsDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  emplois du temps a été obtenu avec succès");
	    	reponse.setResult(absences);
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
