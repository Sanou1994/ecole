package com.app.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Paiement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID idCaissier;
	private String payant;
	private UUID idAnneeScolaire;
	private UUID idEleve;
	private String datePaiement;
	@Column(length = 1000)
	private String commentaire;
	private float montant;
	private Float montant_reduction=0f;
	private String etat;
	private String annee_paiement;
	private String numeroPaiement;
	private Float credit;
	private Float avant;
	private boolean status =true;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;



}
