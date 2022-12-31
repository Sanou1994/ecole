package com.gestion_ecole.ecole.dto.request;

public class FiliereDtoRequest {
	private Long id;
	private String titre;
	private boolean status;
	public FiliereDtoRequest() {
		super();
	}
	public FiliereDtoRequest(Long id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
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
