package com.gestion_ecole.ecole.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.dto.response.FiliereDtoResponse;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.DepartementRepository;
import com.gestion_ecole.ecole.repository.FiliereRepository;
import com.gestion_ecole.ecole.utils.Utility;

@Service
public class FiliereService implements IFiliereService{
@Autowired
private FiliereRepository filiereRepository;

@Autowired
private DepartementRepository departementRepository;


@Override
public Reponse createOrUpdateFiliere(FiliereDtoRequest filiere) {
	Reponse reponse = new Reponse();	

	try
	{   
		Filiere soft = this.filiereRepository.findByTitre(filiere.getTitre());
		if( soft == null || (soft != null && filiere.getDepartementID() != null))
		{
			
			if(filiere.getId() != 0)
			{
				Optional<Filiere> softGot = this.filiereRepository.findById(filiere.getId());

				if(softGot.isPresent())
				{
					softGot.get().setTitre(filiere.getTitre());
					soft.setDepartementID(filiere.getDepartementID());
					filiereRepository.save(softGot.get());
			    	reponse.setMessage(" La filière a été modifiée avec succès !");
			    	reponse.setCode(200);

				}
				else
				{
					reponse.setCode(201);
			    	reponse.setMessage(" La filière n'existe plus !");
				}
				
			}
			else
			{
				filiereRepository.save(Utility.toFiliere(filiere));
				reponse.setCode(200);
		    	reponse.setMessage(" La filière a été créée avec succès !");
		    	reponse.setResult(filiere);
			}
		}
		else
		{
			reponse.setCode(201);
	    	reponse.setMessage(" La filière existe déjà!");
	    	reponse.setResult(filiere);
	    	
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
public Reponse getFiliereById(Long id) {
	Reponse reponse = new Reponse();	

	try
	{   
		Optional<Filiere> soft = this.filiereRepository.findById(id);
		reponse.setCode(200);
    	reponse.setMessage(" La filière  a été obtenue avec succès!");
    	reponse.setResult(Utility.toFiliereDtoResponse(soft.orElse(null)));
    	
	}
	catch (Exception e) 
	{
		reponse.setCode(500);
    	reponse.setMessage(" Une erreur interne est survenue");
	}
	return reponse ;
}

@Override
public Reponse bloquerFiliere(Long id) {
	Reponse reponse = new Reponse();		

	try
	{
		Filiere filiere = filiereRepository.findById(id).get();
	    if(filiere != null)
	    {
	    	filiere.setStatus(false);
	    	Filiere cs=	filiereRepository.save(filiere);
	    	reponse.setCode(200);
	    	reponse.setMessage(" La filière a été supprimée avec succès");
	    	reponse.setResult(Utility.toFiliereDtoResponse(cs));
	    }	
	    else
	    {
	    	reponse.setCode(201);
	    	reponse.setMessage(" Cette classe n'existe pas ");
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
public Reponse ListeFilieres(Long id) {
	Reponse reponse = new Reponse();	

	try
	{ 
		List<Departement> departements = departementRepository.findByStructureID(id);
		List<FiliereDtoResponse> filieres= filiereRepository.findAll()
	                                                      .stream()
	                                                      .filter(p-> departements.stream().filter(f->f.getId() == p.getDepartementID()).count() !=0  )
	                                                      .map(Utility :: toFiliereDtoResponse)
	                                                      .collect(Collectors.toList());
		reponse.setCode(200);
    	reponse.setMessage(" La liste des  filières a été obtenu avec succès");
    	reponse.setResult(filieres);
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
