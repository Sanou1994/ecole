package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.PosteDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IPosteService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class PosteRestControler {
	@Autowired
	private IPosteService posteService;
	
	@PostMapping(Utility.ADD_POSTE)
	public Reponse addPoste( @RequestBody PosteDtoRequest poste){
		Reponse resultatCreation = posteService.createOrUpdatePoste(poste);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_POSTE_BY_ID)
	public Reponse getPosteById(@PathVariable(value = "id") Long posteId){		
		Reponse	PosteUpdate =posteService.getPosteById(posteId);		
		return PosteUpdate ;
    }
	@GetMapping(Utility.GET_ALL_POSTE)
    public Reponse getAllPostes() 
    {
    	Reponse list = posteService.ListePostes();       
        return list;
    }
	@GetMapping(Utility.DELETE_POSTE_BY_ID)
	public Reponse deletePoste(@PathVariable(value = "id") Long posteId){
		Reponse   resultat = posteService.bloquerPoste(posteId);		
			
		return resultat;
    }
}
