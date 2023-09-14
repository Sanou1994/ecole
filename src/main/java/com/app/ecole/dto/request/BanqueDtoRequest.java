package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BanqueDtoRequest
{
	private UUID ID;
	private String nom;
	private String nom_verseur;
	private String numeroPaiment;
	private Float montant;
	private String dateVersement;
	private UUID enregistreurID;



}
