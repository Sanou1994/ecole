package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity @Data
public class Abscence
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private long dateDebut;
private long dateFin;
private boolean status=false;
private long dateAbscenceCreate;
@ManyToOne
private Classe classe;
@Lob
private String raison;
@ManyToOne
private User user;
public Abscence(Long id, long dateDebut, long dateFin, boolean status, long dateAbscenceCreate, Classe classe,
		String raison, User user) {
	super();
	this.id = id;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
	this.status = status;
	this.dateAbscenceCreate = dateAbscenceCreate;
	this.classe = classe;
	this.raison = raison;
	this.user = user;
}
public Abscence() {
	super();
}

}
