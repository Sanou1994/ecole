package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.dto.response.AbsenceDtoResponse;
import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.AbsenceRepository;
import com.gestion_ecole.ecole.utils.Utility;
@Service
public class AbsenceService implements IAbsenceService {
	@Autowired
	AbsenceRepository absenceRepository;

	public AbsenceService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Reponse createOrUpdateAbsence(AbsenceDtoRequest absence) {
		Reponse reponse = new Reponse();	

		try
		{  
				if(absence.getId() != null)
				{
					Optional<Absence> softGot = this.absenceRepository.findById(absence.getId());

					if(softGot.isPresent())
					{
						
						absenceRepository.save(softGot.get());
				    	reponse.setMessage(" L'absence a été modifiée avec succès !");
				    	reponse.setCode(200);

					}
					else
					{
						reponse.setCode(201);
				    	reponse.setMessage(" L'absence n'existe plus !");
					}
					
				}
				else
				{
					absenceRepository.save(Utility.toAbsence(absence));
					reponse.setCode(200);
			    	reponse.setMessage(" L'absence a été créée avec succès !");
			    	reponse.setResult(absence);
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
	public Reponse getAbsenceById(Long id) {
		Reponse reponse = new Reponse();	

		try
		{   
			Optional<Absence> soft = this.absenceRepository.findById(id);
			reponse.setCode(200);
	    	reponse.setMessage(" La classe  a été obtenue avec succès!");
	    	reponse.setResult(Utility.toAbsenceDtoResponse(soft.orElse(null)));
	    	
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	    	reponse.setMessage(" Une erreur interne est survenue");
		}
		return reponse ;
	}

	@Override
	public Reponse deleteAbsence(Long id) {
		Reponse reponse = new Reponse();		

		try
		{
			Absence absence = absenceRepository.findById(id).get();
		    if(absence != null)
		    {
		    	absenceRepository.delete(absence);
		    	reponse.setCode(200);
		    	reponse.setMessage(" L'absence a été supprimée avec succès");
		    	reponse.setResult(Utility.toAbsenceDtoResponse(absence));
		    }	
		    else
		    {
		    	reponse.setCode(201);
		    	reponse.setMessage(" Cette absence n'existe pas ");
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
	public Reponse listeAbsences() {
		Reponse reponse = new Reponse();	

		try
		{   List<AbsenceDtoResponse> absences= absenceRepository.findAll()
		                                                      .stream()
		                                                      .map(Utility :: toAbsenceDtoResponse)
		                                                      .collect(Collectors.toList());
			reponse.setCode(200);
	    	reponse.setMessage(" La liste des  absences a été obtenu avec succès");
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
