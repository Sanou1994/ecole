package com.gestion_ecole.ecole.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
@Entity  
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User  {
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
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
	@Transient
	private String monToken;
	private String login;
	private String password;
	private String compteBancaire;
	@Column(name = "reset_password_token")
    private String resetPasswordToken;
	private String role ; 
	@OneToMany(orphanRemoval = true,targetEntity=Abscence.class, cascade = CascadeType.ALL,mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Abscence> abscences = new ArrayList<Abscence>();
	@OneToMany(orphanRemoval = true,targetEntity=SupportPysique.class,cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	@OneToMany(orphanRemoval = true,targetEntity=Paiement.class, cascade = CascadeType.ALL,mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Paiement> paiements = new ArrayList<Paiement>();
	@OneToMany(orphanRemoval = true,targetEntity=Seance.class,cascade = CascadeType.REMOVE, mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> seances = new ArrayList<Seance>();
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
	public List<Seance> getSceances() {
		return seances;
	}
	public void setSceances(List<Seance> seances) {
		this.seances = seances;
	}	
	
	
}
