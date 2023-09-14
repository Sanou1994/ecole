package com.app.ecole.dto.response;
import com.app.ecole.entities.Solde;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class EleveDetailsDtoResponse {
	private UUID ID;
	private String matricule;
	private Float avant;
	private Float pret;
	private String nom;
	private String prenom;
	private String date_de_naissance;
	private String lieu;
	private String genre;
	private String pays;
	private UUID parentID;
	private String adresse;
	private String email;
	private String telephone;
	private String remarques;
	private String actif;
	private String situation;
	private boolean status;
	private List<Solde> soldes = new ArrayList<>();
	private List<MoisPaiement_TypePaiementResponse> serviceAbonnees = new ArrayList<>();
	private List<MoisPaiement_TypePaiementResponse> servicePayes = new ArrayList<>();
	private Date createdOn;
	private Date updatedOn;

}

