package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.AnneeScolaireDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IAnneScolaireService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class AnneeScolaireRestControler {

	@Autowired
	private IAnneScolaireService anneeScolaireService;
	
	@PostMapping(Utility.ADD_ANNEE_SCOLAIRE)
	public Reponse addAnneeScolaire( @RequestBody AnneeScolaireDtoRequest anneeScolaire){
		Reponse resultatCreation = anneeScolaireService.createOrUpdateAnneScolaire(anneeScolaire);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_ANNEE_SCOLAIRE_BY_ID)
	public Reponse getAnneeScolaireById(@PathVariable(value = "id") Long anneeScolaireId){		
		Reponse	userUpdate =anneeScolaireService.getAnneScolaireById(anneeScolaireId);		
		return userUpdate ;
    }
	@GetMapping(Utility.GET_ALL_ANNEE_SCOLAIRES)
    public Reponse getAllAnneeScolaires(@PathVariable(value = "id") Long anneeScolaireId) {
    	Reponse list = anneeScolaireService.ListeAnneScolaires(anneeScolaireId);       
        return list;
    }
	@GetMapping(Utility.DELETE_ANNEE_SCOLAIRE_BY_ID)
	public Reponse getDeleteAnneeScolaire(@PathVariable(value = "id") Long anneeScolaireId){
				Reponse   resultat = anneeScolaireService.bloquerAnneScolaire(anneeScolaireId);		
			
					return resultat;
    }
}
