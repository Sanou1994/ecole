package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor @Data
public class SeanceDtoRequest {
	private Long id;
	private String type;
	private float coefficient;
	private int nombreHeure;
	private double montantHoraire;
	private boolean status=true;
	private long anneeScolaireID;
	
	
}
