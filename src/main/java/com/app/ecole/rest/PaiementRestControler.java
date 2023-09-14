package com.app.ecole.rest;
import com.app.ecole.dto.request.ModePaiementDtoRequest;
import com.app.ecole.dto.request.PaiementDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.ModePaiementService;
import com.app.ecole.service.PaiementService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaiementRestControler {
	@Autowired
	private PaiementService paiementService;

	@PostMapping(Utility.PAIEMENTS)
	public Reponse getAddTypePaiement(@RequestBody PaiementDtoRequest paiementDtoRequest){
		Reponse resultatCreation = paiementService.create(paiementDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.PAIEMENTS_CAISSIER)
	public Reponse paiementCaisser(
			@RequestParam(value="caissierID",required = false)  UUID caissierID,
			@RequestParam(value="dateDebut",required = false)  Long dateDebut,
			@RequestParam(value="dateFin" ,required = false)  Long dateFin

	){
		Reponse	userUpdate =paiementService.getAllPaiementByUser(caissierID, dateDebut, dateFin);
		return userUpdate ;

	}
	@GetMapping(Utility.PAIEMENTS_CUMUL_SERVICE)
	public Reponse paiementCumulService(
			@RequestParam(value="anneeID",required = false)  UUID anneeID,
			@RequestParam(value="dateDebut",required = false)  Long dateDebut,
			@RequestParam(value="dateFin" ,required = false)  Long dateFin

	){
		Reponse	userUpdate =paiementService.cumulRecetteParService(anneeID,dateDebut,dateFin);
		return userUpdate ;

	}

	@GetMapping(Utility.CHANGE_STATUS_PAIEMENT_BY_ID)
	public Reponse enablePaiement(@PathVariable(value = "id") UUID paiementID){
		Reponse	userUpdate =paiementService.disableById(paiementID);
		return userUpdate ;
	}
	@GetMapping(Utility.PAIEMENT_BY_ID)
	public Reponse getTypePaiementById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =paiementService.getById(groupeID);
		return userUpdate ;
    }

	@GetMapping(Utility.PAIEMENTS)
	public Reponse getAllTypePaiements()
	{
		Reponse   resultat = paiementService.getAll();
		return resultat;
    }
	      
}
