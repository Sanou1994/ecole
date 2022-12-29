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
	}
