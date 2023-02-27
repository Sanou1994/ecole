package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class SeanceDtoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String type;
	private float coefficient;
	private int nombreHeure;
	private double montantHoraire;
	private boolean status;
	private long anneeScolaireID;
	
}
