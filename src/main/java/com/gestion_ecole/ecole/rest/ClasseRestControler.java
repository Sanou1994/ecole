package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IClasseService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class ClasseRestControler {
	@Autowired
	private IClasseService classeService;
	
	@PostMapping(Utility.ADD_CLASSE)
	public Reponse addClasse( @RequestBody ClasseDtoRequest classe){
		Reponse resultatCreation = classeService.createOrUpdateClasse(classe);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_CLASSE_BY_ID)
	public Reponse getClasseById(@PathVariable(value = "id") Long classeId){		
		Reponse	classeUpdate =classeService.getClasseById(classeId);		
		return classeUpdate ;
    }
	@GetMapping(Utility.GET_ALL_CLASSE)
    public Reponse getAllClasses() 
    {
    	Reponse list = classeService.ListeClasses();       
        return list;
    }
	@GetMapping(Utility.DELETE_CLASSE_BY_ID)
	public Reponse deleteClasse(@PathVariable(value = "id") Long classeId){
		Reponse   resultat = classeService.bloquerClasse(classeId);		
			
		return resultat;
    }
}
