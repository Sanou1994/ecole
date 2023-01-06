package com.gestion_ecole.ecole.dto.response;

import java.io.Serializable;

public class UserDtoResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long structureID;
	private String prenom;
	private String nom;
	private String adresse;
	private String numeroMatriciule;
	private String typeDeRecrutement;
	private String type;
	private String sexe;
	private String naissance;
	private long dateCreation;
	private boolean status =true;
	private String nationalite;
	private String login;
	private String email;
	private String telephone;
	private String monToken;
	private String password;
	private String compteBancaire;
    private String resetPasswordToken;
	private String role ;
	private String lieu_naissance;

	
	

	public UserDtoResponse(Long id, Long structureID, String prenom, String nom, String adresse,
			String numeroMatriciule, String typeDeRecrutement, String type, String sexe, String naissance,
			long dateCreation, boolean status, String nationalite, String login, String email, String telephone,
			String monToken, String password, String compteBancaire, String resetPasswordToken, String role,
			String lieu_naissance) {
		super();
		this.id = id;
		this.structureID = structureID;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.numeroMatriciule = numeroMatriciule;
		this.typeDeRecrutement = typeDeRecrutement;
		this.type = type;
		this.sexe = sexe;
		this.naissance = naissance;
		this.dateCreation = dateCreation;
		this.status = status;
		this.nationalite = nationalite;
		this.login = login;
		this.email = email;
		this.telephone = telephone;
		this.monToken = monToken;
		this.password = password;
		this.compteBancaire = compteBancaire;
		this.resetPasswordToken = resetPasswordToken;
		this.role = role;
		this.lieu_naissance = lieu_naissance;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getLieu_naissance() {
		return lieu_naissance;
	}

	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public UserDtoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumeroMatriciule() {
		return numeroMatriciule;
	}
	public void setNumeroMatriciule(String numeroMatriciule) {
		this.numeroMatriciule = numeroMatriciule;
	}
	public String getTypeDeRecrutement() {
		return typeDeRecrutement;
	}
	public void setTypeDeRecrutement(String typeDeRecrutement) {
		this.typeDeRecrutement = typeDeRecrutement;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNaissance() {
		return naissance;
	}
	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}
	public long getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(long dateCreation) {
		this.dateCreation = dateCreation;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMonToken() {
		return monToken;
	}
	public void setMonToken(String monToken) {
		this.monToken = monToken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompteBancaire() {
		return compteBancaire;
	}
	public void setCompteBancaire(String compteBancaire) {
		this.compteBancaire = compteBancaire;
	}
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getStructureID() {
		return structureID;
	}
	public void setStructureID(Long structureID) {
		this.structureID = structureID;
	} 

}

