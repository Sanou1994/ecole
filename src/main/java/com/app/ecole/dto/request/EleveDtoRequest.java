package com.app.ecole.dto.request;

import com.app.ecole.entities.Classe;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Paiement;
import com.app.ecole.entities.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleveDtoRequest
{
	private UUID ID;
	private String matricule;
	private String nom;
	private UUID classeID;
	private String prenom;
	private UUID idParent;
	private ParentDtoRequest parent;
	private String date_de_naissance;
	private String lieu;
	private String pays;
	private String genre;
	private String adresse;
	private String email;
	private String telephone;
	private String remarques;
	private String actif;
	private String situation;
	private boolean status;


}
