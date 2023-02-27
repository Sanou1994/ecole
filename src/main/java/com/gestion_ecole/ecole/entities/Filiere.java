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
public class Filiere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean status=true;
	private String titre;
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "filiere")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Classe> classes = new ArrayList<Classe>();
	@OneToMany(orphanRemoval = true,targetEntity=Personnal.class, cascade = CascadeType.ALL,mappedBy = "filiere")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Personnal> personnals = new ArrayList<Personnal>();
	private Long departementID;
	
	
	public Filiere() {
		super();
	}
	public Filiere(Long id, boolean status, String titre, List<Classe> classes, List<Personnal> personnals,
			Long departementID) {
		super();
		this.id = id;
		this.status = status;
		this.titre = titre;
		this.classes = classes;
		this.personnals = personnals;
		this.departementID = departementID;
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
	public Long getDepartementID() {
		return departementID;
	}
	public void setDepartementID(Long departementID) {
		this.departementID = departementID;
	}
	
	
}
