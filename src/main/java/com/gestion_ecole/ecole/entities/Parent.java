package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Parent")
public class Parent extends User {
	 private String professionParent;
	 private String typeParent;
	 private String relationTuteur;
	 @OneToMany(orphanRemoval = true,targetEntity=Ass_parent_student.class,cascade = CascadeType.ALL)
		@LazyCollection(LazyCollectionOption.FALSE)
		private List<Ass_parent_student> ass_parent_students = new ArrayList<Ass_parent_student>();



	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Parent(String professionParent, String typeParent, String relationTuteur) {
		super();
		this.professionParent = professionParent;
		this.typeParent = typeParent;
		this.relationTuteur = relationTuteur;
	}


	public Parent(String professionParent, String typeParent, String relationTuteur,
			List<Ass_parent_student> ass_parent_students) {
		super();
		this.professionParent = professionParent;
		this.typeParent = typeParent;
		this.relationTuteur = relationTuteur;
		this.ass_parent_students = ass_parent_students;
	}
	public Parent(Long id, Long structureID, String prenom, String nom, String sexe, String adresse,
			String numeroMatriciule, String typeDeRecrutement, Long type, String naissance, String nationalite,
			String lieu_naissance, long dateCreation, boolean status, boolean monPremiereConnexion, String email,
			String telephone, String name_logo, String url_logo, Long contratID, String monToken, String password,
			String compteBancaire, String resetPasswordToken, String role, List<Absence> absences,
			List<SupportPysique> supportPysiques, List<Paiement> paiements, List<Seance> seances, List<Code> codes) {
		super(id, structureID, prenom, nom, sexe, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, nationalite,
				lieu_naissance, dateCreation, status, monPremiereConnexion, email, telephone, name_logo, url_logo, contratID,
				monToken, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements, seances,
				codes);
		// TODO Auto-generated constructor stub
	}
	public String getProfessionParent() {
		return professionParent;
	}
	public void setProfessionParent(String professionParent) {
		this.professionParent = professionParent;
	}
	public String getTypeParent() {
		return typeParent;
	}
	public void setTypeParent(String typeParent) {
		this.typeParent = typeParent;
	}
	public String getRelationTuteur() {
		return relationTuteur;
	}
	public void setRelationTuteur(String relationTuteur) {
		this.relationTuteur = relationTuteur;
	}
	public List<Ass_parent_student> getAss_parent_students() {
		return ass_parent_students;
	}
	public void setAss_parent_students(List<Ass_parent_student> ass_parent_students) {
		this.ass_parent_students = ass_parent_students;
	}
	
	

	
	}
