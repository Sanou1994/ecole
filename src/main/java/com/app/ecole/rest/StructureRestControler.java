package com.app.ecole.rest;
import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.StructureDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.AnneeService;
import com.app.ecole.service.StructureService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class StructureRestControler {
	@Autowired
	private StructureService structureService;

	@PostMapping(Utility.STRUCTURES)
	public Reponse getAddStructure(
			@RequestParam(value="nom",required = false)  String nom,
			@RequestParam(value="logo",required = false) MultipartFile logo,
			@RequestParam(value="favicon" ,required = false)  MultipartFile favicon,
			@RequestParam(value="telephone",required = false)  String telephone,
			@RequestParam(value="adresse",required = false)  String adresse,
			@RequestParam(value="email",required = false)  String email,
			@RequestParam(value="pays",required = false)  String pays)
	{
		StructureDtoRequest structureDtoRequest = new StructureDtoRequest();
		structureDtoRequest.setNom(nom);
		structureDtoRequest.setPays(pays);
		structureDtoRequest.setEmail(email);
		structureDtoRequest.setTelephone(telephone);
		structureDtoRequest.setAdresse(adresse);

		Reponse resultatCreation = structureService.createFile(structureDtoRequest,  logo, favicon);
		return resultatCreation;
    }
	
	@GetMapping(Utility.STRUCTURE_BY_ID)
	public Reponse getStructureById(@PathVariable(value = "id") UUID structureID){
		Reponse	userUpdate =structureService.getById(structureID);
		return userUpdate ;
    }
	@GetMapping(Utility.CHANGE_STATUS_STRUCTURE_BY_ID)
	public Reponse deleteStructureById(@PathVariable(value = "id") UUID structureID){
		Reponse	userUpdate =structureService.disableById(structureID);
		return userUpdate ;
	}



	@GetMapping(Utility.STRUCTURES)
	public Reponse getAllStructures()
	{
		Reponse   resultat = structureService.getAll();
		return resultat;
    }
	      
}
