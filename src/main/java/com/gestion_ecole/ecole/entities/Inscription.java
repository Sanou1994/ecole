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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor 
@NoArgsConstructor @Data
@Table(name="Inscription")
public class Inscription {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id; 
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Student student;
	@ManyToOne
    private Personnal personnal;
	@OneToMany(orphanRemoval = true,targetEntity=SupportPysique.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	private long anneeScolaireID;
	
	
	
	
}
