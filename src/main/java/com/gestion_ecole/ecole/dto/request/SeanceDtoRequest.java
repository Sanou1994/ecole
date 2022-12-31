package com.gestion_ecole.ecole.dto.request;

public class SeanceDtoRequest {
	private Long id;
	private String type;
	private float coefficient;
	private int nombreHeure;
	private double montantHoraire;
	private boolean status=true;
	public SeanceDtoRequest() {
		super();
	}
	public SeanceDtoRequest(Long id, String type, float coefficient, int nombreHeure, double montantHoraire) {
		super();
		this.id = id;
		this.type = type;
		this.coefficient = coefficient;
		this.nombreHeure = nombreHeure;
		this.montantHoraire = montantHoraire;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
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

}
