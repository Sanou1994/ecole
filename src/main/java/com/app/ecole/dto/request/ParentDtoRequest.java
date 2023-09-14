package com.app.ecole.dto.request;

import com.app.ecole.entities.Eleve;
import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDtoRequest
{
	private UUID ID;
	private String type_parent;
	private String nom;
	private String prenom;
	private String fonction;
	private String email;
	private String telephone;
	private boolean status;
	private int nombre_enfants;


}
