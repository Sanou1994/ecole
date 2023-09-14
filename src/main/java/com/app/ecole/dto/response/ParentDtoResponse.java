package com.app.ecole.dto.response;

import com.app.ecole.entities.Eleve;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class ParentDtoResponse {
	private UUID ID;
	private String type_parent;
	private String nom;
	private String prenom;
	private String fonction;
	private String email;
	private String password;
	private String telephone;
	private boolean status;
	private Date createdOn;
	private Date updatedOn;
	private int nombre_enfants;



}

