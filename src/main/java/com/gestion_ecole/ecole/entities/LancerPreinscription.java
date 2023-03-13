package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor 
@NoArgsConstructor @Data
public class LancerPreinscription
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long departementID;
	private long anneeScolaireID;
	private long filiereID;
	private long classeID;
	private long datePrologement;
	private long dateDebut;
	private long dateFin;
	private boolean status=true;	
	private long structureID;

	
}
