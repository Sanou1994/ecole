package com.app.ecole.dto.response;

import com.app.ecole.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class PaiementDtoResponse {
	private UUID ID;
	private UserDtoResponse caissier;
	private String payant;
	private Annee anneeScolaire;
	private EleveDtoResponse eleve;
	private String datePaiement;
	private Float montant_reduction;
	private String commentaire;
	private float montant;
	private String etat;
	private String annee_paiement;
	private List<MoisPaiement_TypePaiementResponse> services = new ArrayList<>();
	private List<Solde_TypePaiementResponse> servicePayes = new ArrayList<>();
	private String numeroPaiement;
	private boolean status;
	private List<ModePaiementDtoResponse> mode_paiements = new ArrayList<>();
	private Float credit;
	private Float avant;
	private Date createdOn;
	private Date updatedOn;
}

