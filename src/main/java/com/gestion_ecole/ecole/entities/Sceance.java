package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="Sceance")
public class Sceance
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private float coefficient;
	private boolean status=true;
	private int nombreHeure;
	private double montantHoraire;
	@ManyToOne
	private User  user;
	@ManyToOne
    private Teacher teacher;
	@ManyToOne
    private Module module;
	@ManyToOne
    private Personnal personnal;
	
	public Sceance() {
		super();
	}
	public Sceance(Long id, String type, float coefficient, boolean status, int nombreHeure, double montantHoraire,
			User user, Teacher teacher, Module module, Personnal personnal) {
		super();
		this.id = id;
		this.type = type;
		this.coefficient = coefficient;
		this.status = status;
		this.nombreHeure = nombreHeure;
		this.montantHoraire = montantHoraire;
		this.user = user;
		this.teacher = teacher;
		this.module = module;
		this.personnal = personnal;
	}
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Personnal getPersonnal() {
		return personnal;
	}
	public void setPersonnal(Personnal personnal) {
		this.personnal = personnal;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
