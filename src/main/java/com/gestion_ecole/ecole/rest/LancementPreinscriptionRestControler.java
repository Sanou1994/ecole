package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.LancerPreinscriptionDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.ILancerPreinscriptionService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class LancementPreinscriptionRestControler {

	@Autowired
	private ILancerPreinscriptionService lancerPreinscriptionService;
	
	@PostMapping(Utility.ADD_LANCEMENT_PREINSCRIPTION)
	public Reponse addDepartement( @RequestBody LancerPreinscriptionDtoRequest departement){
		Reponse resultatCreation = lancerPreinscriptionService.createOrUpdateLancerPreinscription(departement);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_LANCEMENT_PREINSCRIPTION_BY_ID)
	public Reponse getDepartementById(@PathVariable(value = "id") Long departementId){		
		Reponse	userUpdate =lancerPreinscriptionService.getLancerPreinscriptionById(departementId);		
		return userUpdate ;
    }
	@GetMapping(Utility.GET_ALL_LANCEMENT_PREINSCRIPTIONS)
    public Reponse getAllLancerPreinscriptions(@PathVariable(value = "id") Long departementId) {
    	Reponse list = lancerPreinscriptionService.ListeLancerPreinscriptions(departementId);       
        return list;
    }
	@GetMapping(Utility.DELETE_LANCEMENT_PREINSCRIPTION_BY_ID)
	public Reponse getDeleteLancerPreinscription(@PathVariable(value = "id") Long departementId){
				Reponse   resultat = lancerPreinscriptionService.bloquerLancerPreinscription(departementId);		
			
					return resultat;
    }
}
