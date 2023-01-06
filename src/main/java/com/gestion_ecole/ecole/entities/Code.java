package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Code {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String telephone;
	private int code;
	private boolean status=false;
	private long dateConnexion ;
	private Long userID;	

	public Code(Long id, String telephone, int code, boolean status, long dateConnexion, Long userID) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.code = code;
		this.status = status;
		this.dateConnexion = dateConnexion;
		this.userID = userID;
	}

	public Code() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getDateConnexion() {
		return dateConnexion;
	}

	public void setDateConnexion(long dateConnexion) {
		this.dateConnexion = dateConnexion;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	
	
}
