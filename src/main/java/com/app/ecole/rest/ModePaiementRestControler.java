package com.app.ecole.rest;
import com.app.ecole.dto.request.ModePaiementDtoRequest;
import com.app.ecole.dto.request.TypePaiementDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.ModePaiementService;
import com.app.ecole.service.TypePaiementService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ModePaiementRestControler {
	@Autowired
	private ModePaiementService modePaiementService;

	@PostMapping(Utility.MODE_PAIEMENTS)
	public Reponse getAddTypePaiement(@RequestBody ModePaiementDtoRequest modePaiementDtoRequest){
		Reponse resultatCreation = modePaiementService.create(modePaiementDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_MODE_PAIEMENT_BY_ID)
	public Reponse changeTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =modePaiementService.disableById(groupeID);
		return userUpdate ;
	}
	@GetMapping(Utility.MODE_PAIEMENT_BY_ID)
	public Reponse getTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =modePaiementService.getById(groupeID);
		return userUpdate ;
    }

	@GetMapping(Utility.MODE_PAIEMENTS)
	public Reponse getAllTypePaiements()
	{
		Reponse   resultat = modePaiementService.getAll();
		return resultat;
    }
	      
}
