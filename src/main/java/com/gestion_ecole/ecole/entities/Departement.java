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

import lombok.Data;
@Entity @Data
public class Departement {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@OneToMany(orphanRemoval = true,targetEntity=Filiere.class, cascade = CascadeType.ALL,mappedBy = "departement")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Filiere> filieres = new ArrayList<Filiere>();
	@OneToMany(orphanRemoval = true,targetEntity=Student.class,cascade = CascadeType.REMOVE, mappedBy="departement")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student> students = new ArrayList<Student>();
	@OneToMany(orphanRemoval = true,targetEntity=Teacher.class, cascade = CascadeType.ALL,mappedBy = "departement")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher>teachers = new ArrayList<Teacher>();
	@OneToMany(orphanRemoval = true,targetEntity=Paiement.class, cascade = CascadeType.ALL,mappedBy = "departement")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Paiement>paiements = new ArrayList<Paiement>();
		
}
