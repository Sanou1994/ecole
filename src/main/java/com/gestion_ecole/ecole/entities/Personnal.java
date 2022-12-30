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
@Table(name="Personnal")
public class Personnal extends User {
	@ManyToOne
	private Filiere filiere;
	@OneToMany(orphanRemoval = true,targetEntity=Note.class,cascade = CascadeType.REMOVE, mappedBy="personnal")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Note> notes = new ArrayList<Note>();
	@OneToMany(orphanRemoval = true,targetEntity=Seance.class,cascade = CascadeType.REMOVE, mappedBy="personnal")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> seances = new ArrayList<Seance>();

	}
