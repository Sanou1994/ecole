package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity 
@Table(name="Seance")
public class Seance
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private boolean status=true;
	private float coefficient;
	private int nombreHeure;
	private double montantHoraire;
	@ManyToOne
	private User  user;
	@ManyToOne
    private Module module;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
	public int getNombreHeure() {
		return nombreHeure;
	}
	public void setNombreHeure(int nombreHeure) {
		this.nombreHeure = nombreHeure;
	}
	public double getMontantHoraire() {
		return montantHoraire;
	}
	public void setMontantHoraire(double montantHoraire) {
		this.montantHoraire = montantHoraire;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
