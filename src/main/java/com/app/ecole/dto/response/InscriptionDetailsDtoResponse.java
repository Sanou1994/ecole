package com.app.ecole.dto.response;

import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Classe;
import com.app.ecole.entities.Eleve;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class InscriptionDetailsDtoResponse {
	private UUID ID;
	private boolean typeInscription;
	private String dateInscription;
	private float montantScolarite;
	private Eleve eleve;
	private Classe classe;
	private Annee anneeScolaire;
	private float tauxReduction;
	private float montantReduction;
	private boolean status;
	private Date createdOn;
	private Date updatedOn;
}

