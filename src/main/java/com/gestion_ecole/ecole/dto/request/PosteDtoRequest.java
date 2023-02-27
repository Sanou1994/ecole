package com.gestion_ecole.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class PosteDtoRequest {
	private Long id;
	private String nom;
	private boolean status;
	private Long structureID;
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
