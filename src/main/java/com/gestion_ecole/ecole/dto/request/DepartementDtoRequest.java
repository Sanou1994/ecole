package com.gestion_ecole.ecole.dto.request;


public class DepartementDtoRequest {

	private Long id;
	private String nom;
	private boolean status;
	public DepartementDtoRequest() {
		super();
	}
	public DepartementDtoRequest(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
		
}