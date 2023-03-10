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

@Entity  
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User  {
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;
	protected Long structureID;
	protected String prenom;
	protected String nom;	
	protected String sexe;
	protected String adresse;
	protected String numeroMatriciule;
	protected String typeDeRecrutement;
	protected Long type;
	protected String naissance;
	protected String nationalite;
	protected String lieu_naissance;
	protected long dateCreation;
	protected boolean status =true;
	protected boolean monPremiereConnexion =false;
	protected String email;
	protected String telephone;
	protected String name_logo;
    protected String url_logo;
    @Column(name = "contratid", nullable = true)
    protected Long contratID;
	@Transient
	protected String monToken;
	protected String password;
	protected String compteBancaire;
	@Column(name = "reset_password_token")
    protected String resetPasswordToken;
	protected String role ; 
	@OneToMany(orphanRemoval = true,targetEntity=Absence.class, cascade = CascadeType.ALL,mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<Absence> absences = new ArrayList<Absence>();
	@OneToMany(orphanRemoval = true,targetEntity=SupportPysique.class,cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	@OneToMany(orphanRemoval = true,targetEntity=Paiement.class, cascade = CascadeType.ALL,mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<Paiement> paiements = new ArrayList<Paiement>();
	@OneToMany(orphanRemoval = true,targetEntity=Seance.class,cascade = CascadeType.REMOVE, mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<Seance> seances = new ArrayList<Seance>();
	@OneToMany
	protected List<Code> codes = new ArrayList<Code>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Long id, Long structureID, String prenom, String nom, String sexe, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String naissance, String nationalite,
			String lieu_naissance, long dateCreation, boolean status, boolean monPremiereConnexion, String email,
			String telephone, String name_logo, String url_logo, Long contratID, String monToken, String password,
			String compteBancaire, String resetPasswordToken, String role, List<Absence> absences,
			List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances, List<Code> codes) {
		super();
		this.id = id;
		this.structureID = structureID;
		this.prenom = prenom;
		this.nom = nom;
		this.sexe = sexe;
		this.adresse = adresse;
		this.numeroMatriciule = numeroMatriciule;
		this.typeDeRecrutement = typeDeRecrutement;
		this.type = type;
		this.naissance = naissance;
		this.nationalite = nationalite;
		this.lieu_naissance = lieu_naissance;
		this.dateCreation = dateCreation;
		this.status = status;
		this.monPremiereConnexion = monPremiereConnexion;
		this.email = email;
		this.telephone = telephone;
		this.name_logo = name_logo;
		this.url_logo = url_logo;
		this.contratID = contratID;
		this.monToken = monToken;
		this.password = password;
		this.compteBancaire = compteBancaire;
		this.resetPasswordToken = resetPasswordToken;
		this.role = role;
		this.absences = absences;
		this.supportPysiques = supportPysiques;
		this.paiements = paiements;
		this.seances = seances;
		this.codes = codes;
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
	public void setAbscences(List<Absence> abscences) {
		this.absences = abscences;
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
	public void setSceances(List<Seance> sceances) {
		this.seances = sceances;
	}
	
	public List<Code> getCodes() {
		return codes;
	}
	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
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


	public Long getContratID() {
		return contratID;
	}


	public void setContratID(long contratID) {
		this.contratID = contratID;
	}



	public boolean isMonPremiereConnexion() {
		return monPremiereConnexion;
	}



	public void setMonPremiereConnexion(boolean monPremiereConnexion) {
		this.monPremiereConnexion = monPremiereConnexion;
	}	

	
}
