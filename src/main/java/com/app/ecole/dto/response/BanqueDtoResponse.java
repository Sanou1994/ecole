package com.app.ecole.dto.response;

import com.app.ecole.entities.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class BanqueDtoResponse {
	private UUID ID;
	private String nom;
	private String nom_verseur;
	private String numeroPaiment;
	private Float montant;
	private boolean status;
	private String dateVersement;
	private Utilisateur enregistreur;
	private Date createdOn;
	private Date updatedOn;
}

