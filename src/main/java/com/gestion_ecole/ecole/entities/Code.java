package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Code {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String telephone;
	private int code;
	private boolean status=false;
	private long dateConnexion ;

	@ManyToOne
	private User user;
	
	
	
	
	public Code(Long id, String telephone, int code, boolean status, long dateConnexion, User user) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.code = code;
		this.status = status;
		this.dateConnexion = dateConnexion;
		this.user = user;
	}



	public Code() {
		super();
	}
	
	
	
	public long getDateConnexion() {
		return dateConnexion;
	}



	public void setDateConnexion(long dateConnexion) {
		this.dateConnexion = dateConnexion;
	}



	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
