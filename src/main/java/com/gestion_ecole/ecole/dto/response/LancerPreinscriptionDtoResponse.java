package com.gestion_ecole.ecole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class LancerPreinscriptionDtoResponse {
	private Long id;
	private long departementID;
	private long anneeScolaireID;
	private long filiereID;
	private long classeID;
	private long datePrologement;
	private long dateDebut;
	private long dateFin;
	private boolean status;	
	private long structureID;

}
