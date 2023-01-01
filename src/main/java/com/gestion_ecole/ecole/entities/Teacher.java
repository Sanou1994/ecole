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
@Table(name="Teacher")
public class Teacher extends User{
	@OneToMany(targetEntity=Inscription.class,cascade = CascadeType.REMOVE, mappedBy="teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Inscription> inscriptions = new ArrayList<Inscription>();
	@OneToMany(orphanRemoval = true,targetEntity=Student.class, cascade = CascadeType.ALL,mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Classe> classes = new ArrayList<Classe>();
	@ManyToOne
    private Classe classe;
	@ManyToOne
    private Student student;
	@ManyToOne
	private Departement departement;
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(List<Inscription> inscriptions, List<Student> students, List<Classe> classes, Classe classe,
			Student student, Departement departement) {
		super();
		this.inscriptions = inscriptions;
		this.students = students;
		this.classes = classes;
		this.classe = classe;
		this.student = student;
		this.departement = departement;
	}
	public Teacher(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String password, String compteBancaire, String resetPasswordToken,
			String role, List<Absence> absences, List<SupportPysique> supportPysiques, List<Paiement> paiements,
			List<Seance> seances, List<Code> codes) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, password, compteBancaire, resetPasswordToken, role, absences, supportPysiques, paiements,
				seances, codes);
		// TODO Auto-generated constructor stub
	}
	
	

	}
