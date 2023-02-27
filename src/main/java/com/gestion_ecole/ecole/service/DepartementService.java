package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.dto.response.DepartementDtoResponse;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.DepartementRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class DepartementService   implements IDepartementService{
@Autowired
DepartementRepository departementRepository;

@Override
public Reponse createOrUpdateDepartement(DepartementDtoRequest departement) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Departement> soft = this.departementRepository.findByNom(departement.getNom());
		if(!soft.isPresent()) 
		{
			if(departement.getId() != 0)
			{
				Optional<Departement> departementGot = this.departementRepository.findById(departement.getId());

				if(departementGot.isPresent())
				{
					departementGot.get().setNom(departement.getNom());
					departementRepository.save(departementGot.get());
			    	reponse.setMessage(" Le département a été modifié avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" Le département n'existe plus !");
				}
				
			}
			else
			{
				departementRepository.save(Utility.toEntityDepartementFromRequest(departement));
				reponse.setCode(200);
		    	reponse.setMessage(" Le département a été créée avec succès !");
		    	reponse.setResult(departement);
			}
			
		
		}
		else 
		{
			departementRepository.save(Utility.toEntityDepartementFromRequest(departement));
			reponse.setCode(200);
	    	reponse.setMessage(" Le département a été créée avec succès !");
	    	reponse.setResult(departement);
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
public Reponse getDepartementById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Departement> soft = this.departementRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" Le département a été obtenu avec succès!");
    	reponse.setResult(Utility.toDtoDepartementDtoResponse(soft.orElse(null)));
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}

@Override
public Reponse bloquerDepartement(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		Departement departement = departementRepository.findById(id).get();
	    if(departement != null)
	    {
	    	departement.setStatus(false);
	    	departementRepository.save(departement);
	    	reponse.setCode(200);
	    	reponse.setMessage(" Le département a été bloqué avec succès");
	    	reponse.setResult(Utility.toDtoDepartementDtoResponse(departement));
	    }	
	    else
	    {
	    	reponse.setCode(201);
	    	reponse.setMessage(" Ce département n'existe pas ");
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
public Reponse ListeDepartements(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   List<DepartementDtoResponse> departements= departementRepository.findByStructureID(id)
	                                                      .stream()
	                                                      .map(Utility :: toDtoDepartementDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
    	reponse.setMessage(" La liste des  départements a été obtenue avec succès");
    	reponse.setResult(departements);
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
