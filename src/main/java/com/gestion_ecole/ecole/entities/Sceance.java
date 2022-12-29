package com.gestion_ecole.ecole.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity @Data
@Table(name="Sceance")
public class Sceance
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private float coefficient;
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
}
