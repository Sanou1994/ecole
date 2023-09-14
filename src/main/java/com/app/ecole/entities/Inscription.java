package com.app.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Inscription {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID eleveID;
	private UUID classeID;
	private boolean typeInscription;
	private String dateInscription;
	private float tauxReduction;
	private float montantReduction;
	private UUID anneeScolaireID;
	private boolean status;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;
}
