package com.app.ecole.rest;
import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.ReductionDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.AnneeService;
import com.app.ecole.service.ReductionService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ReductionRestControler {
	@Autowired
	private ReductionService anneeService;

	@PostMapping(Utility.REDUCTIONS)
	public Reponse getAddReduction(@RequestBody ReductionDtoRequest anneeDtoRequest){
		Reponse resultatCreation = anneeService.create(anneeDtoRequest);
		return resultatCreation;
    }
	
	@GetMapping(Utility.REDUCTION_BY_ID)
	public Reponse getReductionById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =anneeService.getById(groupeID);
		return userUpdate ;
    }
	@GetMapping(Utility.CHANGE_STATUS_REDUCTION_BY_ID)
	public Reponse deleteReductionById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =anneeService.disableById(groupeID);
		return userUpdate ;
	}



	@GetMapping(Utility.REDUCTIONS)
	public Reponse getAllReductions()
	{
		Reponse   resultat = anneeService.getAll();
		return resultat;
    }
	      
}
