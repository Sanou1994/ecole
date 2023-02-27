package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor 
@NoArgsConstructor @Data
public class Absence
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
private long anneeScolaireID;

}
