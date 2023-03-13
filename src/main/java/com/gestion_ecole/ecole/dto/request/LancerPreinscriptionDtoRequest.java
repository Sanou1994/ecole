package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 @AllArgsConstructor 
@NoArgsConstructor @Data
public class LancerPreinscriptionDtoRequest
{
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
