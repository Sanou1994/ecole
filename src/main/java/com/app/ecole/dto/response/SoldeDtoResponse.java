package com.app.ecole.dto.response;

import com.app.ecole.entities.ServiceImpayes;
import com.app.ecole.entities.Solde;
import com.app.ecole.entities.Solde_TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class SoldeDtoResponse {
	private UUID ID;
	private UUID eleveID;
	private UUID anneeScolaireID;
	private Float credit;
	private Float avant;
	private List<Solde_TypePaiement> servicesAbonnementAnnuels = new ArrayList<>();
	private ServiceImpayes serviceImpayes;
	private boolean status;
	private Date createdOn;
	private Date updatedOn;
}

