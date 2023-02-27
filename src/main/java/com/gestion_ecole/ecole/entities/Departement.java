package com.gestion_ecole.ecole.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity 
public class Departement {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long structureID;
	private boolean status=true;
	private String nom;
	@OneToMany(orphanRemoval = true,targetEntity=Filiere.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Filiere> filieres = new ArrayList<Filiere>();
	@OneToMany(orphanRemoval = true,targetEntity=Student.class,cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=Teacher.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher>teachers = new ArrayList<Teacher>();
	@OneToMany(orphanRemoval = true,targetEntity=Paiement.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Paiement>paiements = new ArrayList<Paiement>();
	
	public Departement() {
		super();
	}
	
	public Departement(Long id, Long structureID, boolean status, String nom, List<Filiere> filieres,
			List<Student> students, List<Teacher> teachers, List<Paiement> paiements) {
		super();
		this.id = id;
		this.structureID = structureID;
		this.status = status;
		this.nom = nom;
		this.filieres = filieres;
		this.students = students;
		this.teachers = teachers;
		this.paiements = paiements;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Filiere> getFilieres() {
		return filieres;
	}
	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Paiement> getPaiements() {
		return paiements;
	}
	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
		
}
