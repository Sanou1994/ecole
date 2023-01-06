package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IEmploiDuTempsService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class EmploiDuTempsRestControler {
	@Autowired
	private IEmploiDuTempsService emploiDuTempsService;
	
	@PostMapping(Utility.ADD_EMPLOIDUTEMPS)
	public Reponse addEmploiDuTemps( @RequestBody EmploiDuTempsDtoRequest emploiDuTempsDtoRequest){
		Reponse resultatCreation = emploiDuTempsService.createOrUpdateEmploiDuTemps(emploiDuTempsDtoRequest);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_EMPLOIDUTEMPS_BY_ID)
	public Reponse getEmploiDuTempsById(@PathVariable(value = "id") Long emploiDuTempsId){		
		Reponse	emploiDuTempsUpdate =emploiDuTempsService.getEmploiDuTempsById(emploiDuTempsId);		
		return emploiDuTempsUpdate ;
    }
	@GetMapping(Utility.GET_ALL_EMPLOIDUTEMPS)
    public Reponse getAllEmploiDuTemps() 
    {
    	Reponse list = emploiDuTempsService.listeEmploiDuTemps();       
        return list;
    }
	@GetMapping(Utility.DELETE_EMPLOIDUTEMPS_BY_ID)
	public Reponse deleteEmploiDuTemps(@PathVariable(value = "id") Long emploiDuTempsId){
		Reponse   resultat = emploiDuTempsService.deleteEmploiDuTemps(emploiDuTempsId);		
			
		return resultat;
    }


}
