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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor 
@NoArgsConstructor @Data
public class EmploiDuTemps {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "emploiDuTemps")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> Sceances = new ArrayList<Seance>(); 
	@ManyToOne
    private Classe classe;
	private long anneeScolaireID;
	
}
