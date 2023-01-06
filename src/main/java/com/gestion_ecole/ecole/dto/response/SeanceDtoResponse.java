package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class SeanceDtoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String type;
	private float coefficient;
	private int nombreHeure;
	private double montantHoraire;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
	public int getNombreHeure() {
		return nombreHeure;
	}
	public void setNombreHeure(int nombreHeure) {
		this.nombreHeure = nombreHeure;
	}
	public double getMontantHoraire() {
		return montantHoraire;
	}
	public void setMontantHoraire(double montantHoraire) {
		this.montantHoraire = montantHoraire;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
