package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="Personnal")
public class Personnal extends User {
	@ManyToOne
	private Filiere filiere;
	@OneToMany(orphanRemoval = true,targetEntity=Note.class,cascade = CascadeType.REMOVE, mappedBy="personnal")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Note> notes = new ArrayList<Note>();
	public Personnal(Filiere filiere, List<Note> notes) {
		super();
		this.filiere = filiere;
		this.notes = notes;
	}
	public Personnal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personnal(Long id, Long structureID, String prenom, String nom, String sexe, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String naissance, String nationalite,
			String lieu_naissance, long dateCreation, boolean status, String email, String telephone, String name_logo,
			String url_logo, long contratID, String monToken, String password, String compteBancaire,
			String resetPasswordToken, String role, List<Absence> absences, List<SupportPysique> supportPysiques,
			List<Paiement> paiements, List<Seance> seances, List<Code> codes) {
		super(id, structureID, prenom, nom, sexe, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, nationalite,
				lieu_naissance, dateCreation, status, email, telephone, name_logo, url_logo, contratID, monToken, password,
				compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements, seances, codes);
		// TODO Auto-generated constructor stub
	}
	
	
		}
