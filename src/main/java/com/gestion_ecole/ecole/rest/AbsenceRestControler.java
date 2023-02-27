package com.gestion_ecole.ecole.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IAbsenceService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class AbsenceRestControler {
	@Autowired
	private IAbsenceService absenceService;
	
	@PostMapping(Utility.ADD_ABSENCE)
	public Reponse addAbsence( @RequestBody AbsenceDtoRequest absence){
		Reponse resultatCreation = absenceService.createOrUpdateAbsence(absence);
		return resultatCreation;
    }

	
	@GetMapping(Utility.GET_ABSENCE_BY_ID)
	public Reponse getAbsenceById(@PathVariable(value = "id") Long absenceId){		
		Reponse	absenceUpdate =absenceService.getAbsenceById(absenceId);		
		return absenceUpdate ;
    }
	@GetMapping(Utility.GET_ALL_ABSENCE)
    public Reponse getAllAbsences() 
    {
    	Reponse list = absenceService.listeAbsences();       
        return list;
    }
	@GetMapping(Utility.DELETE_ABSENCE_BY_ID)
	public Reponse deleteAbsence(@PathVariable(value = "id") Long absenceId){
		Reponse   resultat = absenceService.deleteAbsence(absenceId);		
			
		return resultat;
    }
}

