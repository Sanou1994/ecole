package com.app.ecole.dto.response;

import com.app.ecole.entities.Annee;
import com.app.ecole.entities.Classe;
import com.app.ecole.entities.Type_Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor @Data
public class PaiementSatDtoResponse {
	private Classe classe;
	private Type_Paiement service;
	private Double montantTotal;
}

