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
	
	@OneToMany(orphanRemoval = true,targetEntity=Student.class, cascade = CascadeType.ALL,mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();

	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parent(List<Student> students) {
		super();
		this.students = students;
	}

	public Parent(Long id, String prenom, String nom, String adresse, String numeroMatriciule, String typeDeRecrutement,
			String type, String naissance, long dateCreation, boolean status, String email, String telephone,
			String monToken, String login, String password, String compteBancaire, String resetPasswordToken,
			String role, List<Absence> absences, List<SupportPysique> supportPysiques, List<Paiement> paiements,
			List<Seance> seances, List<Code> codes) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, login, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques,
				paiements, seances, codes);
		// TODO Auto-generated constructor stub
	}

	
	

	
	}
