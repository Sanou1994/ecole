package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePaiementDtoRequest
{
	private UUID ID;
	private String typePaiement;
	private boolean status;
	private Boolean estObligatoire;
	private boolean estAnnuelle;
	private Boolean estScolarite;
	private float montant_mensuel;
	private float montant_annuel;
	private UUID classeMereID;
	private List<UUID> reductions = new ArrayList<>();

}
