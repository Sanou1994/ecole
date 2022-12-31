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
@Table(name="Student")
public class Student extends User{
	@OneToMany(targetEntity=Inscription.class,cascade = CascadeType.REMOVE, mappedBy="student")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Inscription> inscriptions = new ArrayList<Inscription>();
	@OneToMany(orphanRemoval = true,targetEntity=Note.class, cascade = CascadeType.ALL,mappedBy = "student")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Note> notes = new ArrayList<Note>();
	@OneToMany(orphanRemoval = true,targetEntity=Note.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> parains = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=Teacher.class, cascade = CascadeType.ALL,mappedBy = "student")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher> professeurAppuis = new ArrayList<Teacher>();
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Parent parent;
	@ManyToOne
    private Classe classe;
	@ManyToOne
	private Departement departement;
	public Student(List<Inscription> inscriptions, List<Note> notes, List<Student> parains,
			List<Teacher> professeurAppuis, Teacher teacher, Parent parent, Classe classe, Departement departement) {
		super();
		this.inscriptions = inscriptions;
		this.notes = notes;
		this.parains = parains;
		this.professeurAppuis = professeurAppuis;
		this.teacher = teacher;
		this.parent = parent;
		this.classe = classe;
		this.departement = departement;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long id, String prenom, String nom, String adresse, String numeroMatriciule,
			String typeDeRecrutement, String type, String naissance, long dateCreation, boolean status, String email,
			String telephone, String monToken, String login, String password, String compteBancaire,
			String resetPasswordToken, String role, List<Abscence> abscences, List<SupportPysique> supportPysiques,
			List<Paiement> paiements, List<Sceance> sceances) {
		super(id, prenom, nom, adresse, numeroMatriciule, typeDeRecrutement, type, naissance, dateCreation, status, email,
				telephone, monToken, login, password, compteBancaire, resetPasswordToken, role, abscences, supportPysiques,
				paiements, sceances);
		// TODO Auto-generated constructor stub
	}
	
	
	}
