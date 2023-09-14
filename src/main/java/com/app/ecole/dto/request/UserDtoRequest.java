package com.app.ecole.dto.request;

import com.app.ecole.entities.Groupe;
import com.app.ecole.entities.Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest
{
	private UUID ID;
	private String nom;
	private String prenom;
	private String password;
	private boolean status;
	private String etat;
	private String email;
	private String telephone;
	private UUID groupeID;


}
