package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.NiveauEtudeDtoRequest;
import com.gestion_ecole.ecole.dto.response.NiveauEtudeDtoResponse;
import com.gestion_ecole.ecole.entities.Niveau_etude;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.NiveauEtudeRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class NiveauEtudeService   implements INiveauEtudeService{
@Autowired
NiveauEtudeRepository niveauEtudeRepository;

@Override
public Reponse createOrUpdateNiveauEtude(NiveauEtudeDtoRequest niveauEtude) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Niveau_etude> soft = this.niveauEtudeRepository.findByNom(niveauEtude.getNom());
		if(!soft.isPresent()) 
		{
			if(niveauEtude.getId() != 0)
			{
				Optional<Niveau_etude> NiveauEtudeGot = this.niveauEtudeRepository.findById(niveauEtude.getId());

				if(NiveauEtudeGot.isPresent())
				{
					NiveauEtudeGot.get().setNom(niveauEtude.getNom());
					niveauEtudeRepository.save(NiveauEtudeGot.get());
			    	reponse.setMessage(" Le niveau a été modifié avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" Le niveau n'existe plus !");
				}
				
			}
			else
			{
				niveauEtudeRepository.save(Utility.toEntityNiveau_etudeFromRequest(niveauEtude));
				reponse.setCode(200);
		    	reponse.setMessage(" Le niveau a été créée avec succès !");
		    	reponse.setResult(niveauEtude);
			}
			
		
		}
		else 
		{
			niveauEtudeRepository.save(Utility.toEntityNiveau_etudeFromRequest(niveauEtude));
			reponse.setCode(200);
	    	reponse.setMessage(" Le niveau a été créée avec succès !");
	    	reponse.setResult(niveauEtude);
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
public Reponse getNiveauEtudeById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Niveau_etude> soft = this.niveauEtudeRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" Le niveau etude a été obtenu avec succès!");
    	reponse.setResult(Utility.toDtoNiveau_etudeDtoResponse(soft.orElse(null)));
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;

}

@Override
public Reponse bloquerNiveauEtude(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		Niveau_etude niveauEtude = niveauEtudeRepository.findById(id).get();
	    if(niveauEtude != null)
	    {
	    	niveauEtude.setStatus(false);
	    	niveauEtudeRepository.save(niveauEtude);
	    	reponse.setCode(200);
	    	reponse.setMessage(" Le département a été bloqué avec succès");
	    	reponse.setResult(Utility.toDtoNiveau_etudeDtoResponse(niveauEtude));
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
public Reponse ListeNiveauEtude(Long id) 
{
	Reponse reponse = new Reponse();	

	try
	{   List<NiveauEtudeDtoResponse> NiveauEtudes= niveauEtudeRepository.findByStructureID(id)
	                                                      .stream()
	                                                      .map(Utility :: toDtoNiveau_etudeDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
    	reponse.setMessage(" La liste des  niveaux études a été obtenue avec succès");
    	reponse.setResult(NiveauEtudes);
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
