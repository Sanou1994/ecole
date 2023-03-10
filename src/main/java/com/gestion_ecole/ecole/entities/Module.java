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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="Module")
public class Module {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(orphanRemoval = true,targetEntity=Seance.class, cascade = CascadeType.ALL,mappedBy = "module")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> seances = new ArrayList<Seance>();
	@ManyToOne
    private Classe classe;
	public Module(Long id, List<Seance> seances, Classe classe) {
		super();
		this.id = id;
		this.seances = seances;
		this.classe = classe;
	}
	public Module() {
		super();
	}
	
}
