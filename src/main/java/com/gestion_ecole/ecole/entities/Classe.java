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

@Entity @Data
public class Classe
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
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
	@OneToMany(orphanRemoval = true,targetEntity=Abscence.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Abscence> abscences = new ArrayList<Abscence>();
	@OneToMany(orphanRemoval = true,targetEntity=Abscence.class,cascade = CascadeType.REMOVE, mappedBy="classe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CahierDeTexte> cahierDeTextes = new ArrayList<CahierDeTexte>();
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Filiere filiere;
}
