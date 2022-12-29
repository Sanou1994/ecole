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
}
