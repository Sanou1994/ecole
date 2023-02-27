package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Poste
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private boolean status=true;
	private Long structureID;
	
	public Poste() {
		super();
	}
	
	public Poste(Long id, String nom, boolean status, Long structureID) {
		super();
		this.id = id;
		this.nom = nom;
		this.status = status;
		this.structureID = structureID;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getStructureID() {
		return structureID;
	}

	public void setStructureID(Long structureID) {
		this.structureID = structureID;
	}
	
	
}
