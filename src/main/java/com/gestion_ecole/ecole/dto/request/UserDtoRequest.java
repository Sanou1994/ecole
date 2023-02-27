package com.gestion_ecole.ecole.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.SupportPysique;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
public class UserDtoRequest{
	private Long id;
	private Long structureID;
	private String prenom;
	private String name_logo;
    private String url_logo;
	private String nom;
	private String adresse;
	private String numeroMatriciule;
	private String typeDeRecrutement; 
	private String typeUser;
	private Long type;
	private String naissance;
	private String sexe;
	private String nationalite;
	private long dateCreation;	
	private long contratID;
	private boolean status =true;
	private String lieu_naissance;
	private String email;
	private String telephone;
	private String monToken;
	private String password;
	private String compteBancaire;
    private String resetPasswordToken;
	private String role ; 
	private List<Absence> absences = new ArrayList<Absence>();
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	private List<Paiement> paiements = new ArrayList<Paiement>();
	private List<Seance> seances = new ArrayList<Seance>();
	
	
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





	public String getTypeUser() {
		return typeUser;
	}



	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}



	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}



	public Long getStructureID() {
		return structureID;
	}


	public void setStructureID(Long structureID) {
		this.structureID = structureID;
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



	public Long getType() {
		return type;
	}



	public void setType(Long type) {
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



	public List<Absence> getAbscences() {
		return absences;
	}



	public void setAbscences(List<Absence> abcences) {
		this.absences = abcences;
	}



	public List<SupportPysique> getSupportPysiques() {
		return supportPysiques;
	}



	public void setSupportPysiques(List<SupportPysique> supportPysiques) {
		this.supportPysiques = supportPysiques;
	}



	public List<Paiement> getPaiements() {
		return paiements;
	}



	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}



	public List<Seance> getSeances() {
		return seances;
	}



	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}


	public String getName_logo() {
		return name_logo;
	}


	public void setName_logo(String name_logo) {
		this.name_logo = name_logo;
	}


	public String getUrl_logo() {
		return url_logo;
	}


	public void setUrl_logo(String url_logo) {
		this.url_logo = url_logo;
	}


	public long getContratID() {
		return contratID;
	}


	public void setContratID(long contratID) {
		this.contratID = contratID;
	}

	
}
