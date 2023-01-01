package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity 
public class Classe
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private boolean status=true;
	@OneToMany(orphanRemoval = true,targetEntity=Module.class, cascade = CascadeType.ALL,mappedBy = "classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Module> modules = new ArrayList<Module>();
	@OneToMany(orphanRemoval = true,targetEntity=Student.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=EmploiDuTemps.class, cascade = CascadeType.ALL,mappedBy = "classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<EmploiDuTemps>emploiDuTemps = new ArrayList<EmploiDuTemps>();
	@OneToMany(orphanRemoval = true,targetEntity=Teacher.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher> professeurs = new ArrayList<Teacher>();
	@OneToMany(orphanRemoval = true,targetEntity=Absence.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Absence> abscences = new ArrayList<Absence>();
	@OneToMany(orphanRemoval = true,targetEntity=Absence.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CahierDeTexte> cahierDeTextes = new ArrayList<CahierDeTexte>();
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Filiere filiere;
	
	public Classe() {
		super();
	}
	public Classe(Long id, String nom, boolean status, List<Module> modules, List<Student> students,
			List<EmploiDuTemps> emploiDuTemps, List<Teacher> professeurs, List<Absence> abscences,
			List<CahierDeTexte> cahierDeTextes, Teacher teacher, Filiere filiere) {
		super();
		this.id = id;
		this.nom = nom;
		this.status = status;
		this.modules = modules;
		this.students = students;
		this.emploiDuTemps = emploiDuTemps;
		this.professeurs = professeurs;
		this.abscences = abscences;
		this.cahierDeTextes = cahierDeTextes;
		this.teacher = teacher;
		this.filiere = filiere;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<EmploiDuTemps> getEmploiDuTemps() {
		return emploiDuTemps;
	}
	public void setEmploiDuTemps(List<EmploiDuTemps> emploiDuTemps) {
		this.emploiDuTemps = emploiDuTemps;
	}
	public List<Teacher> getProfesseurs() {
		return professeurs;
	}
	public void setProfesseurs(List<Teacher> professeurs) {
		this.professeurs = professeurs;
	}
	public List<Absence> getAbscences() {
		return abscences;
	}
	public void setAbscences(List<Absence> abscences) {
		this.abscences = abscences;
	}
	public List<CahierDeTexte> getCahierDeTextes() {
		return cahierDeTextes;
	}
	public void setCahierDeTextes(List<CahierDeTexte> cahierDeTextes) {
		this.cahierDeTextes = cahierDeTextes;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
	
}
