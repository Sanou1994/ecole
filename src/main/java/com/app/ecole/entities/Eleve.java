package com.app.ecole.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Eleve {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID parentID;
	private String matricule;
	private String nom;
	private String genre;
	private String prenom;
	private String date_de_naissance;
	private String lieu;
	private String pays;
	private String adresse;
	private String email;
	private String telephone;
	@Column(length = 1000)
	private String remarques;
	private String password;
	private String actif;
	private String situation;
	private boolean status=true;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;




}
