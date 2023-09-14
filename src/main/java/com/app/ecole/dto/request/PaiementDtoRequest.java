package com.app.ecole.dto.request;

import com.app.ecole.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDtoRequest
{
	private UUID ID;
	private UUID idCaissier;
	private String payant;
	private UUID idEleve;
	private String datePaiement;
	private String commentaire;
	private Float credit;
	private Float avant;
	private Float montant_reduction;
	private float montant;
	private String etat;
	private String annee_paiement;
	private List<MoisPaiement_TypePaiement> mois_paiements = new ArrayList<>();
	private boolean status;
	private List<ModePaiement_Paiement> mode_paiements = new ArrayList<>();

}
