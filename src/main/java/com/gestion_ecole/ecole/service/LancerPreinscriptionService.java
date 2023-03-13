package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.LancerPreinscriptionDtoRequest;
import com.gestion_ecole.ecole.dto.response.LancerPreinscriptionDtoResponse;
import com.gestion_ecole.ecole.entities.LancerPreinscription;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.DepartementRepository;
import com.gestion_ecole.ecole.repository.LancerPreinscriptionRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class LancerPreinscriptionService   implements ILancerPreinscriptionService{
@Autowired
DepartementRepository departementRepository;
@Autowired
LancerPreinscriptionRepository lancerPreinscriptionRepository;
@Override
public Reponse createOrUpdateLancerPreinscription(LancerPreinscriptionDtoRequest departement) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<LancerPreinscription> soft=lancerPreinscriptionRepository.findByDepartementIDAndAnneeScolaireIDAndFiliereIDAndClasseIDAndDatePrologementAndDateDebutAndDateFin(departement.getDepartementID(),departement.getAnneeScolaireID(),departement.getFiliereID(),departement.getClasseID(),departement.getDatePrologement(),departement.getDateDebut(),departement.getDateFin());

		if(soft.isPresent()) 
		{
			if(departement.getId() != 0)
			{
				Optional<LancerPreinscription> departementGot = this.lancerPreinscriptionRepository.findById(departement.getId());

				if(departementGot.isPresent())
				{
					departementGot.get().setAnneeScolaireID(departement.getAnneeScolaireID());
					departementGot.get().setClasseID(departement.getClasseID());
					departementGot.get().setDateDebut(departement.getDateDebut());
					departementGot.get().setDateFin(departement.getDateFin());
					departementGot.get().setDatePrologement(departement.getDatePrologement());
					departementGot.get().setDepartementID(departement.getDepartementID());
					departementGot.get().setFiliereID(departement.getFiliereID());
					departementGot.get().setStatus(departement.isStatus());
					departementGot.get().setStructureID(departement.getStructureID());					
					lancerPreinscriptionRepository.save(departementGot.get());
			    	reponse.setMessage(" le lancement de la pré-inscription a été modifié avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" Le lancement de la pré-inscription n'existe plus !");
				}
				
			}
			else
			{
				reponse.setCode(201);
		    	reponse.setMessage(" Le lancement de la pré-inscription existe déjà !");
		    }
			
		
		}
		else 
		{
			if(departement.getId() != 0)
			{
				Optional<LancerPreinscription> departementGot = this.lancerPreinscriptionRepository.findById(departement.getId());

				if(departementGot.isPresent())
				{
					departementGot.get().setAnneeScolaireID(departement.getAnneeScolaireID());
					departementGot.get().setClasseID(departement.getClasseID());
					departementGot.get().setDateDebut(departement.getDateDebut());
					departementGot.get().setDateFin(departement.getDateFin());
					departementGot.get().setDatePrologement(departement.getDatePrologement());
					departementGot.get().setDepartementID(departement.getDepartementID());
					departementGot.get().setFiliereID(departement.getFiliereID());
					departementGot.get().setStatus(departement.isStatus());
					departementGot.get().setStructureID(departement.getStructureID());					
					lancerPreinscriptionRepository.save(departementGot.get());
			    	reponse.setMessage(" le lancement de la pré-inscription a été modifié avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" Le lancement de la pré-inscription n'existe plus !");
				}
				
			}
			else
			{
				lancerPreinscriptionRepository.save(Utility.toEntityLancerPreinscriptionFromRequest(departement));
				reponse.setCode(200);
		    	reponse.setMessage(" le lancement de la pré-inscription a reussi !");
		    	reponse.setResult(departement);
		    }			
			
			
			
			
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
public Reponse getLancerPreinscriptionById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<LancerPreinscription> soft = this.lancerPreinscriptionRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" Le Lancer Preinscription a été obtenu avec succès!");
    	reponse.setResult(Utility.toDtoLancerPreinscriptionDtoResponse(soft.orElse(null)));
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}

@Override
public Reponse bloquerLancerPreinscription(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		LancerPreinscription departement = lancerPreinscriptionRepository.findById(id).get();
	    if(departement != null)
	    {
	    	departement.setStatus(false);
	    	lancerPreinscriptionRepository.save(departement);
	    	reponse.setCode(200);
	    	reponse.setResult(Utility.toDtoLancerPreinscriptionDtoResponse(departement));
	    }	
	    else
	    {
	    	reponse.setCode(201);
	    	reponse.setMessage(" Ce lacement n'existe pas ");
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
public Reponse ListeLancerPreinscriptions(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   List<LancerPreinscriptionDtoResponse> departements= lancerPreinscriptionRepository.findByStructureID(id)
	                                                      .stream()
	                                                      .map(Utility :: toDtoLancerPreinscriptionDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
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
