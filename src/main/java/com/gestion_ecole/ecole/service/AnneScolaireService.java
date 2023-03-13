package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.AnneeScolaireDtoRequest;
import com.gestion_ecole.ecole.dto.response.AnneeScolaireDtoResponse;
import com.gestion_ecole.ecole.entities.AnneeScolaire;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.AnneScolaireRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class AnneScolaireService   implements IAnneScolaireService{
@Autowired
AnneScolaireRepository anneScolaireRepository;

@Override
public Reponse createOrUpdateAnneScolaire(AnneeScolaireDtoRequest anneScolaire) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<AnneeScolaire> soft = this.anneScolaireRepository.findByLibelle(anneScolaire.getLibelle());
		if(!soft.isPresent()) 
		{
			if(anneScolaire.getId() != 0)
			{
				Optional<AnneeScolaire> anneScolaireGot = this.anneScolaireRepository.findById(anneScolaire.getId());

				if(anneScolaireGot.isPresent())
				{
					anneScolaireGot.get().setLibelle(anneScolaire.getLibelle());
					anneScolaireRepository.save(anneScolaireGot.get());
			    	reponse.setMessage(" L'année scolaire a été modifiée avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" L'année scolaire n'existe plus !");
				}
				
			}
			else
			{
				anneScolaireRepository.save(Utility.toEntityAnneeScolaireFromRequest(anneScolaire));
				reponse.setCode(200);
		    	reponse.setMessage(" L'année scolaire a été créée avec succès !");
		    	reponse.setResult(anneScolaire);
			}
			
		
		}
		else 
		{
			anneScolaireRepository.save(Utility.toEntityAnneeScolaireFromRequest(anneScolaire));
			reponse.setCode(200);
	    	reponse.setMessage(" Le département a été créée avec succès !");
	    	reponse.setResult(anneScolaire);
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
public Reponse getAnneScolaireById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<AnneeScolaire> soft = this.anneScolaireRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" L'année scolaire a été obtenue avec succès!");
    	reponse.setResult(Utility.toDtoAnneeScolaireDtoResponse(soft.orElse(null)));
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}

@Override
public Reponse bloquerAnneScolaire(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		AnneeScolaire anneScolaire = anneScolaireRepository.findById(id).get();
	    if(anneScolaire != null)
	    {
	    	anneScolaire.setStatus(false);
	    	anneScolaireRepository.save(anneScolaire);
	    	reponse.setCode(200);
	    	reponse.setMessage(" L'année scoalire a été bloquée avec succès");
	    	reponse.setResult(Utility.toDtoAnneeScolaireDtoResponse(anneScolaire));
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
public Reponse ListeAnneScolaires(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   List<AnneeScolaireDtoResponse> anneScolaires= anneScolaireRepository.findByStructureID(id)
	                                                      .stream()
	                                                      .map(Utility :: toDtoAnneeScolaireDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
    	reponse.setMessage(" La liste des  scolaires a été obtenue avec succès");
    	reponse.setResult(anneScolaires);
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
