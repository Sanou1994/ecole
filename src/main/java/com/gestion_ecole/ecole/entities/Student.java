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
    private Classe classe;
	private Long departementID;
	private Long filiereID;
	private Long niveauEtudeID;	
	private Long parentID;	
	public Student() {
		super();
	}
	public Student(List<Inscription> inscriptions, List<Note> notes, List<Student> parains,
			List<Teacher> professeurAppuis, Teacher teacher, Classe classe, Long departementID, Long filiereID,
			Long niveauEtudeID, Long parentID) {
		super();
		this.inscriptions = inscriptions;
		this.notes = notes;
		this.parains = parains;
		this.professeurAppuis = professeurAppuis;
		this.teacher = teacher;
		this.classe = classe;
		this.departementID = departementID;
		this.filiereID = filiereID;
		this.niveauEtudeID = niveauEtudeID;
		this.parentID = parentID;
	}
	
	
	public Student(Long id, Long structureID, String prenom, String nom, String sexe, String adresse,
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
	public List<Inscription> getInscriptions() {
		return inscriptions;
	}
	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public List<Student> getParains() {
		return parains;
	}
	public void setParains(List<Student> parains) {
		this.parains = parains;
	}
	public List<Teacher> getProfesseurAppuis() {
		return professeurAppuis;
	}
	public void setProfesseurAppuis(List<Teacher> professeurAppuis) {
		this.professeurAppuis = professeurAppuis;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Long getDepartementID() {
		return departementID;
	}
	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}
	public Long getFiliereID() {
		return filiereID;
	}
	public void setFiliereID(Long filiereID) {
		this.filiereID = filiereID;
	}
	public Long getNiveauEtudeID() {
		return niveauEtudeID;
	}
	public void setNiveauEtudeID(Long niveauEtudeID) {
		this.niveauEtudeID = niveauEtudeID;
	}
	public Long getParentID() {
		return parentID;
	}
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	
	
	}
