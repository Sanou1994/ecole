package com.app.ecole.dto.response;

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
public class UserDtoResponse {
	private UUID ID;
	private String nom;
	private String prenom;
	private String password;
	private boolean status;
	private String etat;
	private String email;
	private String telephone;
	private Groupe groupe;
	private Date createdOn;
	private Date updatedOn;
}

