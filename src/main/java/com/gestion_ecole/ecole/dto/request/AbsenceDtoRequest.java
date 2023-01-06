package com.gestion_ecole.ecole.dto.request;

public class AbsenceDtoRequest {
	private Long id;
	private long dateDebut;
	private long dateFin;
	private boolean status=false;
	private long dateAbscenceCreate;
	
	public AbsenceDtoRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(long dateDebut) {
		this.dateDebut = dateDebut;
	}

	public long getDateFin() {
		return dateFin;
	}

	public void setDateFin(long dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getDateAbscenceCreate() {
		return dateAbscenceCreate;
	}

	public void setDateAbscenceCreate(long dateAbscenceCreate) {
		this.dateAbscenceCreate = dateAbscenceCreate;
	}

	
}
