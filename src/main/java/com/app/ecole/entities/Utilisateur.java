package com.app.ecole.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Utilisateur
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private String nom;
	private String prenom;
	private String password;
	private boolean status=true;
	private String etat;
	private String email;
	private String telephone;
	private UUID groupeID;
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

}
