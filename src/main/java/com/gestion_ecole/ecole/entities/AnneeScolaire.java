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
public class AnneeScolaire
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private String libelle;
private boolean status=true;
private long structureID;

}
