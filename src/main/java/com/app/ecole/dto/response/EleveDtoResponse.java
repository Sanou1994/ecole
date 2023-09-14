package com.app.ecole.dto.response;
import com.app.ecole.entities.Classe;
import com.app.ecole.entities.ServiceImpayes;
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
public class EleveDtoResponse {
	private UUID ID;
	private String matricule;
	private String nom;
	private String prenom;
	private String date_de_naissance;
	private String lieu;
	private String pays;
	private String genre;
	private UUID parentID;
	private String adresse;
	private String email;
	private String telephone;
	private String remarques;
	private String actif;
	private String situation;
	private Classe classe;
	private boolean status;
	private List<Solde> soldes = new ArrayList<>();
	private List<Solde_TypePaiementResponse> servicePayes = new ArrayList<>();
	private List<TypePaiementDtoResponse> serviceAbonnees = new ArrayList<>();
	private ServiceImpayesResponse serviceImpayes = new ServiceImpayesResponse() ;
	private Date createdOn;
	private Date updatedOn;

}

