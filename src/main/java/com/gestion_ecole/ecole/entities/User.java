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
@Entity  @Data
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
	@OneToMany(orphanRemoval = true,targetEntity=Sceance.class,cascade = CascadeType.REMOVE, mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Sceance> sceances = new ArrayList<Sceance>();	
	
}
