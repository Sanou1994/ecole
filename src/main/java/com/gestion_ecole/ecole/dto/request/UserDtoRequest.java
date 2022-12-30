package com.gestion_ecole.ecole.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Abscence;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Sceance;
import com.gestion_ecole.ecole.entities.SupportPysique;


public class UserDtoRequest{
	private Long id;
	private String prenom;
	private String nom;
	private String adresse;
	private String numeroMatriciule;
	private String typeDeRecrutement;
	private String type;
	private String naissance;
	private long dateCreation;
	private boolean status =true;
	private String email;
	private String telephone;
	private String monToken;
	private String login;
	private String password;
	private String compteBancaire;
    private String resetPasswordToken;
	private String role ; 
	private List<Abscence> abscences = new ArrayList<Abscence>();
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	private List<Paiement> paiements = new ArrayList<Paiement>();
	private List<Sceance> sceances = new ArrayList<Sceance>();
	public UserDtoRequest(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String login, String password, String compteBancaire,
			String resetPasswordToken, String role, List<Abscence> abscences, List<SupportPysique> supportPysiques,
			List<Paiement> paiements, List<Sceance> sceances) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.numeroMatriciule = numeroMatriciule;
		this.typeDeRecrutement = typeDeRecrutement;
		this.type = type;
		this.naissance = naissance;
		this.dateCreation = dateCreation;
		this.status = status;
		this.email = email;
		this.telephone = telephone;
		this.monToken = monToken;
		this.login = login;
		this.password = password;
		this.compteBancaire = compteBancaire;
		this.resetPasswordToken = resetPasswordToken;
		this.role = role;
		this.abscences = abscences;
		this.supportPysiques = supportPysiques;
		this.paiements = paiements;
		this.sceances = sceances;
	}
	public UserDtoRequest() {
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public List<Abscence> getAbscences() {
		return abscences;
	}
	public void setAbscences(List<Abscence> abscences) {
		this.abscences = abscences;
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
	public List<Sceance> getSceances() {
		return sceances;
	}
	public void setSceances(List<Sceance> sceances) {
		this.sceances = sceances;
	}
	
	
}
