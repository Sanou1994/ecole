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
	public Personnal(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String login, String password, String compteBancaire,
			String resetPasswordToken, String role, List<Absence> abscences, List<SupportPysique> supportPysiques,
			List<Paiement> paiements, List<Seance> sceances, List<Code> codes) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, login, password, compteBancaire, resetPasswordToken, role, abscences, supportPysiques,
				paiements, sceances, codes);
		// TODO Auto-generated constructor stub
	}
	
	
	
		}
