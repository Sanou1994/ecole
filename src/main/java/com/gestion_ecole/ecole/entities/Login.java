package com.gestion_ecole.ecole.entities;

public class Login {
	
	private String telephone;
	private String password;
	private String email;
	public Login(String telephone, String password, String email) {
		super();
		this.telephone = telephone;
		this.password = password;
		this.email = email;
	}
	
	public Login() {
		super();
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
