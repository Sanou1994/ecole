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

@Entity 
public class EmploiDuTemps {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	@OneToMany(orphanRemoval = true,targetEntity=Classe.class, cascade = CascadeType.ALL,mappedBy = "emploiDuTemps")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seance> Sceances = new ArrayList<Seance>(); 
	@ManyToOne
    private Classe classe;
	
	public EmploiDuTemps() {
		super();
	}
	public EmploiDuTemps(Long id, String titre, List<Seance> sceances, Classe classe) {
		super();
		this.id = id;
		this.titre = titre;
		Sceances = sceances;
		this.classe = classe;
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
	public List<Seance> getSceances() {
		return Sceances;
	}
	public void setSceances(List<Seance> Sceances) {
		this.Sceances = Sceances;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	
}
