package com.app.ecole.dto.response;

import com.app.ecole.entities.Classe;
import com.app.ecole.entities.Reduction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class TypePaiementDtoResponse {
	private UUID ID;
	private UUID classeMereID;
	private List<Reduction> reductions = new ArrayList<>();
	private String typePaiement;
	private float montant_mensuel;
	private Boolean estScolarite;
	private Boolean estObligatoire;
	private float montant_annuel;
	private boolean status;
	private boolean estAnnuelle;
	private Classe classeMere;
	private Date createdOn;
	private Date updatedOn;
}

