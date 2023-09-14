package com.app.ecole.rest;
import com.app.ecole.dto.request.GroupeDtoRequest;
import com.app.ecole.dto.request.TypePaiementDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.GroupeService;
import com.app.ecole.service.TypePaiementService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TypePaiementRestControler {
	@Autowired
	private TypePaiementService typePaiementService;

	@PostMapping(Utility.TYPE_PAIEMENTS)
	public Reponse getAddTypePaiement(@RequestBody TypePaiementDtoRequest typePaiementDtoRequest){
		Reponse resultatCreation = typePaiementService.create(typePaiementDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_TYPE_PAIEMENT_BY_ID)
	public Reponse changeTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =typePaiementService.disableById(groupeID);
		return userUpdate ;
	}
	@GetMapping(Utility.TYPE_PAIEMENT_BY_ID)
	public Reponse getTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =typePaiementService.getById(groupeID);
		return userUpdate ;
    }
	@DeleteMapping(Utility.TYPE_PAIEMENT_BY_ID)
	public Reponse deleteTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =typePaiementService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.TYPE_PAIEMENTS)
	public Reponse getAllTypePaiements()
	{
		Reponse   resultat = typePaiementService.getAll();
		return resultat;
    }
	      
}
