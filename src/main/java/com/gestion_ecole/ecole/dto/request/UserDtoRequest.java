package com.gestion_ecole.ecole.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.gestion_ecole.ecole.entities.Abscence;
import com.gestion_ecole.ecole.entities.Paiement;
import com.gestion_ecole.ecole.entities.Sceance;
import com.gestion_ecole.ecole.entities.SupportPysique;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
public class UserDtoRequest{
	private Long id;
	private String prenom;
	private String nom;
	private String adresse;
	private String numeroMatriciule;
	private String typeDeRecrutement;
	private String type;
	private String naissance;
	private long dateCreation;
	private boolean status =true;
	private String email;
	private String telephone;
	private String monToken;
	private String login;
	private String password;
	private String compteBancaire;
    private String resetPasswordToken;
	private String role ; 
	private List<Abscence> abscences = new ArrayList<Abscence>();
	private List<SupportPysique> supportPysiques = new ArrayList<SupportPysique>();
	private List<Paiement> paiements = new ArrayList<Paiement>();
	private List<Sceance> sceances = new ArrayList<Sceance>();
}
