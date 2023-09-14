package com.app.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @AllArgsConstructor
@NoArgsConstructor @Data
public class Solde_TypePaiement
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID ID;
	private UUID typePaiement;
	private Float montantRestant;
	private String etat;
	private boolean status=true;
	private boolean isAnnuelle;
	private UUID dernierPaiementID;
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private List<String> mois_payes = new ArrayList<>();
	private String dernierMoisPaye;
	private String avantDernierMoisPaye;
	@CreationTimestamp
	private Date createdOn;
	@UpdateTimestamp
	private Date updatedOn;
}
