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
	@OneToMany(orphanRemoval = true,targetEntity=Seance.class, cascade = CascadeType.ALL,mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> seances = new ArrayList<Seance>();
	@ManyToOne
    private Classe classe;
	@ManyToOne
    private Student student;
	@ManyToOne
	private Departement departement;

	}
