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

import lombok.Data;

@Entity
public class Filiere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "filiere")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Classe> classes = new ArrayList<Classe>();
	@OneToMany(orphanRemoval = true,targetEntity=Personnal.class, cascade = CascadeType.ALL,mappedBy = "filiere")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Personnal> personnals = new ArrayList<Personnal>();
	@ManyToOne
	private Departement departement;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<Classe> getClasses() {
		return classes;
	}
	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}
	public List<Personnal> getPersonnals() {
		return personnals;
	}
	public void setPersonnals(List<Personnal> personnals) {
		this.personnals = personnals;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
