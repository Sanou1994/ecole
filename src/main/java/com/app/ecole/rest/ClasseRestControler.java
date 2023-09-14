package com.app.ecole.rest;
import com.app.ecole.dto.request.AnneeDtoRequest;
import com.app.ecole.dto.request.ClasseDtoRequest;
import com.app.ecole.entities.Reponse;
import com.app.ecole.service.AnneeService;
import com.app.ecole.service.ClasseService;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ClasseRestControler {
	@Autowired
	private ClasseService classeService;

	@PostMapping(Utility.CLASSES)
	public Reponse getAddUser(@RequestBody ClasseDtoRequest classeDtoRequest){
		Reponse resultatCreation = classeService.create(classeDtoRequest);
		return resultatCreation;
    }
	@GetMapping(Utility.CHANGE_STATUS_CLASSE_BY_ID)
	public Reponse delete(@PathVariable(value = "id") UUID classeID){
		Reponse	userUpdate =classeService.disableById(classeID);
		return userUpdate ;
	}
	@GetMapping(Utility.CLASSE_ELEVES_NON_EN_REGLE)
	public Reponse getElevesNonEnReglesByClasseID(@PathVariable(value = "id") UUID classeID){
		Reponse	userUpdate =classeService.getAllElevesNonEnReglesByClasseID(classeID);
		return userUpdate ;
	}
	@GetMapping(Utility.CLASSE_BY_ID)
	public Reponse getUserById(@PathVariable(value = "id") UUID groupeID){
		Reponse	userUpdate =classeService.getById(groupeID);
		return userUpdate ;
    }

	@GetMapping(Utility.SOUS_CLASSE_BY_ID)
	public Reponse getAllSousClasses(@PathVariable(value = "id") UUID sousClassesID)
	{
		Reponse   resultat = classeService.getAllSousClasse(sousClassesID);
		return resultat;
	}


	@GetMapping(Utility.CLASSES)
	public Reponse getAllUsers()
	{
		Reponse   resultat = classeService.getAll();
		return resultat;
    }
	      
}
