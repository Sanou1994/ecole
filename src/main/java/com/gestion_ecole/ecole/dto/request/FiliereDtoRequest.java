package com.gestion_ecole.ecole.dto.request;

public class FiliereDtoRequest {
	private Long id;
	private String titre;
	private boolean status;
	private Long departementID;
	public FiliereDtoRequest() {
		super();
	}
	
	public FiliereDtoRequest(Long id, String titre, boolean status, Long departementID) {
		super();
		this.id = id;
		this.titre = titre;
		this.status = status;
		this.departementID = departementID;
	}

	public Long getDepartementID() {
		return departementID;
	}

	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
