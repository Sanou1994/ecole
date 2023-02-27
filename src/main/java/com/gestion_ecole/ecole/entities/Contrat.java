package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Contrat
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String categorie;
	private double montant;
	private boolean status=true;
	private Long structureID;
	private Long duree;
	public Contrat() {
		super();
	}	

	
	public Contrat(Long id, String type, String categorie, double montant, boolean status, Long structureID,
			Long duree) {
		super();
		this.id = id;
		this.type = type;
		this.categorie = categorie;
		this.montant = montant;
		this.status = status;
		this.structureID = structureID;
		this.duree = duree;
	}


	public Long getDuree() {
		return duree;
	}


	public void setDuree(Long duree) {
		this.duree = duree;
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



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public double getMontant() {
		return montant;
	}



	public void setMontant(double montant) {
		this.montant = montant;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public Long getStructureID() {
		return structureID;
	}



	public void setStructureID(Long structureID) {
		this.structureID = structureID;
	}



	
	
}
